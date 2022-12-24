package com.hmdp.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.hmdp.utils.RedisConstants.*;
import static com.hmdp.utils.RedisConstants.CACHE_SHOP_TTL;

@Component
public class CacheUtil {

    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 存储对象到redis中
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     */
    public void set(String key, Object value, Long time, TimeUnit timeUnit){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, timeUnit);
    }

    /**
     * 存储对象到redis中，并设置逻辑过期时间
     * @param key
     * @param value
     * @param expireTime
     * @param timeUnit
     */
    public void setExpire(String key, Object value, Long expireTime, TimeUnit timeUnit){
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(timeUnit.toSeconds(expireTime)));
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

    /**
     * 利用泛型，通过设置空值的方式，解决redis的缓存穿透问题
     * @param keyPrefix 缓存键前缀
     * @param id 数据id
     * @param type 结果类型
     * @param function 查询数据库的函数
     * @param time 缓存时间
     * @param unit 缓存单位
     * @param <R> 结果类型
     * @param <ID> ID类型
     * @return
     */
    public <R, ID> R getResultWithPassThrough(String keyPrefix, ID id, Class<R> type, Function<ID, R> function, long time, TimeUnit unit){
        // 1.判断redis中是否存在信息
        String key = keyPrefix + id;
        String json = stringRedisTemplate.opsForValue().get(key);
        // 2.缓存存在，直接返回
        if(StringUtils.isNotBlank(json)){
            return JSONUtil.toBean(json, type);
        }
        /** 解决缓存穿透：如果查到了空值，说明数据库和缓存中都不存在该数据，直接返回 **/
        if("".equals(json)){
            return null;
        }
        // 3.缓存不存在，从数据库中查询
        R r = function.apply(id);
        // 4.数据库中不存在，返回商铺不存在错误
        if(r == null){
            /** 解决缓存穿透：将空值放入缓存中 **/
            stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }
        // 5.数据库中存在，并放入缓存中
        this.set(key, r, time, unit);
        // 6.返回
        return r;
    }

    /**
     * 基于逻辑过期解决缓存击穿
     * @param keyPrefix 缓存键前缀
     * @param lockKeyPrefix 锁的前缀
     * @param id 查询id
     * @param type 结果类型
     * @param function 查询方法
     * @param time 逻辑过期时间
     * @param unit 过期时间单位
     * @param <R>
     * @param <ID>
     * @return
     */
    public <R, ID> R getResultWithLogicalExpire(String keyPrefix, String lockKeyPrefix, ID id, Class<R> type, Function<ID, R> function, long time, TimeUnit unit) {
        // 1.判断redis中是否存在缓存信息
        String key = keyPrefix + id;
        String json = stringRedisTemplate.opsForValue().get(key);
        // 2.缓存不存在，直接返回
        if(StringUtils.isBlank(json)){
            return null;
        }
        // 3.缓存存在，判断缓存是否过期
        RedisData redisData = JSONUtil.toBean(json, RedisData.class);
        JSONObject jsonObject = (JSONObject) redisData.getData();
        R r = JSONUtil.toBean(jsonObject, type);
        // 4.如果过期了，重建缓存
        if(redisData.getExpireTime().isBefore(LocalDateTime.now())){
            // 5.获取互斥锁
            String lockKey = lockKeyPrefix + id;
            boolean getLock = tryLock(lockKey);
            // 6.获取互斥锁成功
            if(getLock){
                // 7.double check
                json = stringRedisTemplate.opsForValue().get(key);
                redisData = JSONUtil.toBean(json, RedisData.class);
                if(redisData.getExpireTime().isBefore(LocalDateTime.now())){
                    // 8.新建线程，重建缓存过期时间
                    CACHE_REBUILD_EXECUTOR.submit(() -> {
                        try {
                            /** 模拟延迟 **/
                            Thread.sleep(200L);
                            R rr = function.apply(id);
                            this.setExpire(key, rr, time, unit);
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
        return r;
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
}
