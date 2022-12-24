package com.hmdp;

import com.hmdp.entity.Shop;
import com.hmdp.service.impl.ShopServiceImpl;
import io.lettuce.core.api.async.RedisGeoAsyncCommands;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.ReactiveGeoCommands;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.hmdp.utils.RedisConstants.SHOP_GEO_KEY;

@Slf4j
@SpringBootTest
class LoadShopData {
    @Autowired
    private ShopServiceImpl shopService;

    @Resource
    private RedissonClient redissonClient;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void loadShopData() {
        // 查询所有商铺
        List<Shop> shops = shopService.query().list();
        // 将商铺按照类型分组
        Map<Long, List<Shop>> typeShops = shops.stream().collect(Collectors.groupingBy(Shop::getTypeId));
        List<RedisGeoCommands.GeoLocation<String>> locations;
        // 放入redis中
        for (Map.Entry<Long, List<Shop>> longListEntry : typeShops.entrySet()) {
            List<Shop> shops1 = longListEntry.getValue();
            locations = new ArrayList<>(shops1.size());
            for (Shop shop : shops1) {
                locations.add(new RedisGeoCommands.GeoLocation<>(shop.getId().toString(),
                        new Point(shop.getX(), shop.getY())));
            }
            stringRedisTemplate.opsForGeo().add(SHOP_GEO_KEY + longListEntry.getKey().toString(), locations);
        }
    }
}
