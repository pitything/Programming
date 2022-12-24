package com.hmdp.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.BooleanUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.jdbc.UncategorizedSQLException;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class LockUtil {
    // 模块名
    private String name;
    private StringRedisTemplate stringRedisTemplate;
    private String KEY_PREFIX = "lock:";
    // 服务器id
    private String machineId = UUID.randomUUID().toString(true) + "-";
    // 加载lua脚本
    private static DefaultRedisScript<Long> UNLOCK_SCRIPT;
    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("/scripts/unlock.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    public LockUtil(String name, StringRedisTemplate stringRedisTemplate) {
        this.name = name;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public boolean tryLock(Long expireTime, TimeUnit unit){
        Long id = Thread.currentThread().getId();
        Boolean getlock = stringRedisTemplate.opsForValue().setIfAbsent(KEY_PREFIX + name, machineId + id, expireTime, unit);
        return BooleanUtil.isTrue(getlock);
    }

    // 当前线程只可以自己线程的锁
    public void unlock(){
        // 使用lua脚本保证了原子性
         stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(KEY_PREFIX + name),
                machineId + Thread.currentThread().getId());
    }
    // public void unlock(){
    //     // 1.获取线程标识
    //     String threadId = machineId + Thread.currentThread().getId();
    //     // 2.获取锁标识
    //     String lockId = stringRedisTemplate.opsForValue().get(KEY_PREFIX + name);
    //     // 3.判断是否是当前线程的锁
    //     if(lockId.equals(threadId)) {
    //         // 4.一致则释放
    //         stringRedisTemplate.delete(KEY_PREFIX + name);
    //     }
    // }
}
