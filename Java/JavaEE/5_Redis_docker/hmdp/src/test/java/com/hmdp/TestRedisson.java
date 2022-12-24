package com.hmdp;

import com.hmdp.service.impl.ShopServiceImpl;
import com.hmdp.utils.RedisIdWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootTest
class TestRedisson {
    @Autowired
    private ShopServiceImpl shopService;

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void testReEnter() {
        RLock lock = redissonClient.getLock("test:redisson");
        boolean holdlock = lock.tryLock();
        if (!holdlock) {
            log.info("获取锁失败！");
        }
        try {
            log.info("获取锁成功");
            method2(lock);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            log.info("释放锁成功");
        }
    }

    private void method2(RLock lock) {
        boolean holdlock = lock.tryLock();
        if (!holdlock) {
            log.info("获取锁失败！");
        }
        try {
            log.info("获取锁成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            log.info("释放锁成功");
        }
    }
}
