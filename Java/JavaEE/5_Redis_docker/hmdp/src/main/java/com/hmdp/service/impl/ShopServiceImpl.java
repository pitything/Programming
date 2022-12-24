package com.hmdp.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.CacheUtil;
import com.hmdp.utils.RedisConstants;
import com.hmdp.utils.RedisData;
import com.hmdp.utils.SystemConstants;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import static com.hmdp.utils.RedisConstants.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CacheUtil cacheUtil;

    @Override
    public Result queryShopById(Long id) {
        Shop shop = null;

        /** 基于设置空值解决缓存穿透 **/
        // shop = cacheUtil.getResultWithPassThrough(CACHE_SHOP_KEY, id, Shop.class, this::getById, CACHE_SHOP_TTL, TimeUnit.MINUTES);

        /** 基于互斥锁解决缓存击穿 **/
        // return queryShopByIdWithMutex(id);

        /** 基于逻辑过期解决缓存击穿 **/
        // return queryShopByIdWithLogicalExpire(id);
        shop = cacheUtil.getResultWithLogicalExpire(CACHE_SHOP_KEY, LOCK_SHOP_KEY, id, Shop.class, this::getById, 10L, TimeUnit.SECONDS);

        if(shop == null) {
            return Result.fail("商铺不存在！");
        }
        return Result.ok(shop);
    }

    /**
     * 基于逻辑过期解决缓存击穿
     * @param id
     * @return
     */
    public Result queryShopByIdWithLogicalExpire(Long id) {
        // 1.判断redis中是否存在商铺信息
        String key = CACHE_SHOP_KEY + id;
        String shopStr = stringRedisTemplate.opsForValue().get(key);
        // 2.商铺不存在，直接返回
        if(StringUtils.isBlank(shopStr)){
            return Result.ok("商铺不存在! ");
        }
        // 3.商铺存在，判断商铺缓存是否过期
        RedisData redisData = JSONUtil.toBean(shopStr, RedisData.class);
        JSONObject shopJsonObj = (JSONObject) redisData.getData();
        Shop shop = JSONUtil.toBean(shopJsonObj, Shop.class);
        // 4.如果过期了，重建缓存
        if(redisData.getExpireTime().isBefore(LocalDateTime.now())){
            // 5.获取互斥锁
            String lockKey = LOCK_SHOP_KEY + id;
            boolean getLock = tryLock(lockKey);
            // 6.获取互斥锁成功
            if(getLock){
                // 7.double check
                shopStr = stringRedisTemplate.opsForValue().get(key);
                redisData = JSONUtil.toBean(shopStr, RedisData.class);
                if(redisData.getExpireTime().isBefore(LocalDateTime.now())){
                    // 8.新建线程，重建缓存过期时间
                    CACHE_REBUILD_EXECUTOR.submit(() -> {
                        try {
                            Thread.sleep(200L);
                            saveShop2Redis(id, 10L);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } finally {
                            // 9.释放锁
                            unlock(lockKey);
                        }
                    });
                }
            }
        }
        // 10.将数据进行返回
        return Result.ok(shop);
    }

    /**
     * 基于互斥锁解决缓存击穿
     * @param id
     * @return
     */
    public Result queryShopByIdWithMutex(Long id) {
        // 1.判断redis中是否存在商铺信息
        String key = CACHE_SHOP_KEY + id;
        String shopStr = stringRedisTemplate.opsForValue().get(key);
        // 2.商铺存在，直接返回
        Shop shop = null;
        if(StringUtils.isNotBlank(shopStr)){
            shop = JSONUtil.toBean(shopStr, Shop.class);
            return Result.ok(shop);
        }
        /** 解决缓存穿透：如果查到了空值，说明数据库和缓存中都不存在该数据，直接返回 **/
        if("".equals(shopStr)){
            return Result.ok("商铺不存在! ");
        }
        // 3.商铺不存在，从数据库中查询
        /** 解决缓存击穿：获取互斥锁，重建缓存 **/
        String lockKey = LOCK_SHOP_KEY + id;
        try {
            boolean getLock = tryLock(lockKey);
            /** 解决缓存击穿：没有获取到互斥锁，休眠并重试 **/
            if(!getLock){
                Thread.sleep(200);
                return queryShopById(id);
            }
            /** 解决缓存击穿：获取到互斥锁，先double check，再次查询缓存 **/
            shopStr = stringRedisTemplate.opsForValue().get(key);
            if(StringUtils.isNotBlank(shopStr)){
                shop = JSONUtil.toBean(shopStr, Shop.class);
                return Result.ok(shop);
            }
            if("".equals(shopStr)){
                return Result.ok("商铺不存在! ");
            }
            /** 解决缓存击穿：从数据库中查询数据 **/
            shop = getById(id);
            // 4.数据库中不存在，返回商铺不存在错误
            if(shop == null){
                /** 解决缓存穿透：将空值放入缓存中 **/
                stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
                return Result.fail("商铺不存在！");
            }
            // 5.数据库中存在，并放入缓存中
            stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop), CACHE_SHOP_TTL, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            /** 解决缓存击穿：释放互斥锁 **/
            unlock(lockKey);
        }
        // 6.返回
        return Result.ok(shop);
    }

    /**
     * 获取互斥锁
     * 使用setnx的方式
     * @param key
     * @return
     */
    public boolean tryLock(String key){
        Boolean getLock = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", LOCK_SHOP_TTL, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(getLock);
    }

    /**
     * 释放互斥锁
     * @param key
     */
    public void unlock(String key){
        stringRedisTemplate.delete(key);
    }

    /**
     * 将商铺信息缓存到redis中，进行缓存预热
     * @param id 商铺id
     * @param expireSeconds 逻辑过期时间
     */
    public void saveShop2Redis(Long id, Long expireSeconds){
        Shop shop = getById(id);
        RedisData redisData = new RedisData();
        redisData.setData(shop);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(expireSeconds));
        stringRedisTemplate.opsForValue().set(CACHE_SHOP_KEY + id, JSONUtil.toJsonStr(redisData));
    }

    @Override
    @Transactional
    public Result updateShop(Shop shop) {
        Long id = shop.getId();
        if(id == null){
            return Result.fail("商铺id不能为空!");
        }
        // 1.更新数据库
        updateById(shop);
        // 2.删除缓存
        stringRedisTemplate.delete(CACHE_SHOP_KEY + id);
        return Result.ok();
    }

    @Override
    public Result queryShopByType(Integer typeId, Integer current, Double x, Double y) {
        // 1.判断是否需要根据坐标查询
        if (x == null || y == null) {
            // 不需要坐标查询，按数据库查询
            Page<Shop> page = query()
                    .eq("type_id", typeId)
                    .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
            // 返回数据
            return Result.ok(page.getRecords());
        }

        // 2.计算分页参数
        int from = (current - 1) * SystemConstants.DEFAULT_PAGE_SIZE;
        int end = current * SystemConstants.DEFAULT_PAGE_SIZE;

        // 3.查询redis、按照距离排序、分页。结果：shopId、distance
        String key = SHOP_GEO_KEY + typeId;
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = stringRedisTemplate.opsForGeo() // GEOSEARCH key BYLONLAT x y BYRADIUS 10 WITHDISTANCE
                .search(
                        key,
                        GeoReference.fromCoordinate(x, y),
                        new Distance(500000000),
                        RedisGeoCommands.GeoSearchCommandArgs.newGeoSearchArgs().includeDistance().limit(end)
                );
        // 4.解析出id
        if (results == null) {
            return Result.ok(Collections.emptyList());
        }
        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> list = results.getContent();
        if (list.size() <= from) {
            // 没有下一页了，结束
            return Result.ok(Collections.emptyList());
        }
        // 4.1.截取 from ~ end的部分
        List<Long> ids = new ArrayList<>(list.size());
        Map<String, Distance> distanceMap = new HashMap<>(list.size());
        list.stream().skip(from).forEach(result -> {
            // 4.2.获取店铺id
            String shopIdStr = result.getContent().getName();
            ids.add(Long.valueOf(shopIdStr));
            // 4.3.获取距离
            Distance distance = result.getDistance();
            distanceMap.put(shopIdStr, distance);
        });
        // 5.根据id查询Shop
        String idStr = StrUtil.join(",", ids);
        List<Shop> shops = query().in("id", ids).last("ORDER BY FIELD(id," + idStr + ")").list();
        for (Shop shop : shops) {
            shop.setDistance(distanceMap.get(shop.getId().toString()).getValue());
        }
        // 6.返回
        return Result.ok(shops);
    }
}
