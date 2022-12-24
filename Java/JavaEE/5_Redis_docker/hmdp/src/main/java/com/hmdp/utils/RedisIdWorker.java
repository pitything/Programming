package com.hmdp.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class RedisIdWorker  {
    // 开始时间：2022.1.1 00:00:00
    private final long BEG_TIME = 1640995200;
    // 左移位数
    private final int LEFT_MOVE_COUNT = 32;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public long getNextId(String keyPrefix){
        // 1.获取时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowLong = now.toEpochSecond(ZoneOffset.UTC);
        long timeStamp = nowLong - BEG_TIME;

        // 2.获取redis自增id
        String nowStr = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        Long redisId = stringRedisTemplate.opsForValue().increment("inc:" + keyPrefix + ":" + nowStr);

        // 3.拼接时间戳和自增id
        return timeStamp << LEFT_MOVE_COUNT | redisId;
    }

    // public static void main(String[] args) {
    //     LocalDateTime localDateTime = LocalDateTime.of(2022, 1, 1, 0, 0, 0);
    //     System.out.println(localDateTime.toEpochSecond(ZoneOffset.UTC));
    // }


}
