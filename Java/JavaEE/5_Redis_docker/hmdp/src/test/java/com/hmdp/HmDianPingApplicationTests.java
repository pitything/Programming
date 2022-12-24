package com.hmdp;

import com.hmdp.service.IShopService;
import com.hmdp.service.impl.ShopServiceImpl;
import com.hmdp.utils.RedisIdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class HmDianPingApplicationTests {
    @Autowired
    private ShopServiceImpl shopService;

    @Resource
    private RedisIdWorker redisIdWorker;

    private final ExecutorService executorService = Executors.newFixedThreadPool(500);

    @Test
    public void testRedisIDWorker() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3000);
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                long id = redisIdWorker.getNextId("order:id");
                // System.out.println(id);
            }
            countDownLatch.countDown();
        };

        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            executorService.submit(runnable);
        }
        countDownLatch.await();
        long l2 = System.currentTimeMillis();
        System.out.println("耗时： " + (l2 - l1));
    }

    @Test
    public void testSaveShop2Redis(){
        shopService.saveShop2Redis(1L, 10L);
    }
}
