package com.hmdp;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class TestHyperLogLog {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testHyperLogLog(){

        String[] arr = new String[1000];
        for(int i = 0; i < 1000000; i++){
            int index = i % 1000;
            if(i == 0 || index != 0){
                arr[index] = "user_" + i;
            }else{
                stringRedisTemplate.opsForHyperLogLog().add("hll", arr);
            }
        }
        Long count = stringRedisTemplate.opsForHyperLogLog().size("hll");
        //  准确率： 995759 / 1000000
        System.out.println("count = " + count);
    }
}
