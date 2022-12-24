package com.hmdp.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.entity.ShopType;
import com.hmdp.mapper.ShopTypeMapper;
import com.hmdp.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result queryTypeList() {
        // 1.查询redis中是否存在商铺类型列表
        String key = RedisConstants.CACHE_SHOP_TYPE_KEY;
        String shopTypes = stringRedisTemplate.opsForValue().get(key);
        // 2.如果存在，直接返回
        List<ShopType> shopList = null;
        if(StringUtils.isNotBlank(shopTypes)){
            shopList = JSONUtil.toList(shopTypes, ShopType.class);
            return Result.ok(shopList);
        }
        // 3.如果不存在，从数据库中查询
        shopList = query().list();
        // 4.数据库中不存在，返回错误信息：商铺列表为空
        if(shopList == null || shopList.isEmpty()){
            return Result.fail("商铺列表为空！");
        }
        // 5.数据库中存在，查询列表并存到redis中
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shopList));
        // 6.返回结果
        return Result.ok(shopList);
    }
}
