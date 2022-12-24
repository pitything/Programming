package com.itheima;

import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ClientTestApplicationTests {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testValue() {
        redisTemplate.opsForValue().set("name", "张三");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

    @Test
    public void testObjectValue(){
        redisTemplate.opsForValue().set("user1str", new User("aaa", 24));
        Map<String, Object> map = new HashMap<>();
        map.put("name", "aaa");
        map.put("age", "24");
        redisTemplate.opsForHash().putAll("mapstr", map);
        System.out.println(redisTemplate.opsForValue().get("user1str"));
    }
}