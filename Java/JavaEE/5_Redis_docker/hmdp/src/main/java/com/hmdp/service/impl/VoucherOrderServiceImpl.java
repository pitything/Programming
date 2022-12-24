package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.SeckillVoucher;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.mapper.VoucherOrderMapper;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisIdWorker;
import com.hmdp.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j
@Service
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {

    @Autowired
    private ISeckillVoucherService seckillVoucherService;

    @Autowired
    private RedisIdWorker redisIdWorker;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;


    private String streamName = "stream.orders";
    private static final DefaultRedisScript<Long> SECKILL_SCRIPT;
    static {
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        SECKILL_SCRIPT.setLocation(new ClassPathResource("/scripts/seckill.lua"));
        SECKILL_SCRIPT.setResultType(Long.class);
    }

    IVoucherOrderService proxy;
    private ExecutorService pool = Executors.newSingleThreadExecutor();

    // 每次服务初始化就开始执行
    @PostConstruct
    private void init(){
        pool.submit(new HandleVoucherOrdersThread());
    }

    // 通过消息队列异步创建订单
    private class HandleVoucherOrdersThread implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    // 1.redis消息队列中获取订单 XREADGROUP GROUP g1 c1 COUNT 1 BLOCK 2000 STREAMS s1 >
                    List<MapRecord<String, Object, Object>> read = stringRedisTemplate.opsForStream().read(
                            Consumer.from("g1", "c1"),
                            StreamReadOptions.empty().count(1).block(Duration.ofSeconds(2)),
                            StreamOffset.create(streamName, ReadOffset.lastConsumed())
                    );
                    // 2.如果获取不成功，说明没有消息，继续下一个循环
                    if(read == null || read.isEmpty()){
                        continue;
                    }
                    // 3.获取成功，则创建订单
                    MapRecord<String, Object, Object> record = read.get(0);
                    Map<Object, Object> voucherMap = record.getValue();
                    VoucherOrder voucherOrder = BeanUtil.fillBeanWithMap(voucherMap, new VoucherOrder(), true);
                    createVoucherOrder(voucherOrder);

                    // 4.ACK确认
                    stringRedisTemplate.opsForStream().acknowledge(streamName, "g1", record.getId());
                } catch (Exception exception) {
                    log.info("处理订单异常：" + exception.getMessage());
                    // 如果消息队列处理异常了，则从pengding-list中获取异常消息继续处理
                    while (true) {
                        try {
                            // 1.获取pending-list中的订单信息 XREADGROUP GROUP g1 c1 COUNT 1 BLOCK 2000 STREAMS s1 0
                            List<MapRecord<String, Object, Object>> list = stringRedisTemplate.opsForStream().read(
                                    Consumer.from("g1", "c1"),
                                    StreamReadOptions.empty().count(1),
                                    StreamOffset.create(streamName, ReadOffset.from("0"))
                            );
                            // 2.判断订单信息是否为空
                            if (list == null || list.isEmpty()) {
                                // 如果为null，说明没有异常消息，结束循环
                                break;
                            }
                            // 解析数据
                            MapRecord<String, Object, Object> record = list.get(0);
                            Map<Object, Object> value = record.getValue();
                            VoucherOrder voucherOrder = BeanUtil.fillBeanWithMap(value, new VoucherOrder(), true);
                            // 3.创建订单
                            createVoucherOrder(voucherOrder);
                            // 4.确认消息 XACK
                            stringRedisTemplate.opsForStream().acknowledge(streamName, "g1", record.getId());
                        } catch (Exception e) {
                            log.error("处理pendding订单异常", e);
                            try{
                                Thread.sleep(20);
                            }catch(Exception e2){
                                e2.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    // 使用阻塞队列异步创建订单
    private BlockingQueue<VoucherOrder> voucherOrderTasks = new ArrayBlockingQueue<>(1024 * 1024);
    // private class HandleVoucherOrdersThread implements Runnable{
    //     @Override
    //     public void run() {
    //         while(true){
    //             try {
    //                 // 1.从阻塞队列中获取订单
    //                 VoucherOrder voucherOrder = voucherOrderTasks.take();
    //                 // 2.创建订单
    //                 createVoucherOrder(voucherOrder);
    //             } catch (Exception e) {
    //                 log.info("处理订单异常：" + e.getMessage());
    //             }
    //         }
    //     }
    // }

    private void createVoucherOrder(VoucherOrder voucherOrder) {
        RLock lock = redissonClient.getLock("order:" + voucherOrder.getUserId());
        boolean getLock = lock.tryLock();
        if(!getLock){
            return;
        }
        try {
            /** 使用代理对象调用，避免@Transanctional事务注解失效 **/
            proxy.getResult(voucherOrder);
        } finally {
            lock.unlock();
        }
    }
    @Transactional
    public void getResult(VoucherOrder voucherOrder) {
        // 6.新增一人一单限制
        Integer count = query().eq("user_id", voucherOrder.getUserId()).eq("voucher_id", voucherOrder.getVoucherId())
                .count();
        if(count > 1){
            log.info("一个用户只可以下单一次！");
        }

        // 6.并将秒杀券数量-1(添加乐观锁)
        boolean success = seckillVoucherService.update().setSql("stock = stock - 1")
                .eq("voucher_id", voucherOrder.getVoucherId())
                // .eq("stock", secKillVoucher.getStock())
                .gt("stock", 0)
                .update();
        if(!success){
            log.info("库存不足！");
        }

        // 7.库存充足，创建订单
        save(voucherOrder);
    }

    @Override
    public Result seckillVoucher(Long voucherId) throws InterruptedException {
        // 秒杀优化前代码
        // return seckillVoucher1(voucherId);

        // 秒杀优化
        // 1.执行lua脚本，判断是否具有购买资格，若有资格则发送一个消息到redis消息队列中，不再使用阻塞队列了
        Long userId = UserHolder.getUser().getId();
        // 订单id
        long orderId = redisIdWorker.getNextId("order");
        Long res = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                userId.toString(), voucherId.toString(), String.valueOf(orderId));
        // 2.判断是否有资格下单：订单是否充足，是否已经购买过
        if(res != 0){
            return Result.fail(res == 1 ? "库存不足！" : "不能重复下单！");
        }

        proxy = (IVoucherOrderService)AopContext.currentProxy();

        // 5.返回订单ID
        return Result.ok(orderId);
    }

    // 秒杀优化，移步操作数据库
    public Result seckillVoucher2(Long voucherId) throws InterruptedException {
        // 秒杀优化前代码
        // return seckillVoucher1(voucherId);

        // 秒杀优化
        // 1.执行lua脚本
        Long userId = UserHolder.getUser().getId();
        Long res = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                userId.toString(), voucherId.toString());
        // 2.判断是否有资格下单：订单是否充足，是否已经购买过
        if(res != 0){
            return Result.fail(res == 1 ? "库存不足！" : "不能重复下单！");
        }
        // 3.为0，有资格下单
        long orderId = redisIdWorker.getNextId("order");
        // 4. 异步下单，将订单放入阻塞队列中
        VoucherOrder voucherOrder = new VoucherOrder();
        voucherOrder.setVoucherId(voucherId);
        voucherOrder.setUserId(userId);
        voucherOrder.setId(orderId);
        voucherOrderTasks.add(voucherOrder);

        proxy = (IVoucherOrderService)AopContext.currentProxy();

        // 5.返回订单ID
        return Result.ok(orderId);
    }

    public Result seckillVoucher1(Long voucherId) throws InterruptedException {
        // 1.查询优惠券信息
        SeckillVoucher secKillVoucher = seckillVoucherService.getById(voucherId);
        // 2.判断秒杀是否开始
        if(LocalDateTime.now().isBefore(secKillVoucher.getBeginTime())){
            return Result.fail("秒杀还未开始！");
        }
        // 3.判断秒杀是否结束
        if(LocalDateTime.now().isAfter(secKillVoucher.getEndTime())){
            return Result.fail("秒杀已经结束！");
        }
        // 4.判断库存是否充足
        // 5.不充足，则返回错误信息：库存不足！
        if(secKillVoucher.getStock() < 1){
            return Result.fail("库存不足！");
        }
        Long userId = UserHolder.getUser().getId();

        /** 一人一单问题解决：使用synchronized加上锁，存在分布式事务的问题 **/
        // synchronized (userId.toString().intern()) {
        //     /** 使用代理对象调用，避免@Transanctional事务注解失效 **/
        //     IVoucherOrderService  proxy = (IVoucherOrderService)AopContext.currentProxy();
        //     return proxy.getResult(voucherId);
        // }

        /** 一人一单问题解决：使用redis加分布式锁，解决分布式问题 **/
        // LockUtil lock = new LockUtil("order:" + userId, stringRedisTemplate);
        // boolean getLock = lock.tryLock(1L, TimeUnit.MINUTES);
        /** 一人一单问题解决：使用redisson加分布式锁，解决分布式问题 **/
        RLock lock = redissonClient.getLock("order:" + userId);
        boolean getLock = lock.tryLock();
        if(!getLock){
            return Result.fail("一个用户只可以下单一次！");
        }
        try {
            /** 使用代理对象调用，避免@Transanctional事务注解失效 **/
            IVoucherOrderService  proxy = (IVoucherOrderService)AopContext.currentProxy();
            return proxy.getResult(voucherId);
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public Result getResult(Long voucherId) {
        // 6.新增一人一单限制
        Long userId = UserHolder.getUser().getId();
        Integer count = query().eq("user_id", userId).eq("voucher_id", voucherId)
                // .eq(LocalDateTime.now().toString(), LocalDateTime.now())
                .count();
        if(count > 1){
            return Result.fail("一个用户只可以下单一次！");
        }

        // 6.并将秒杀券数量-1(添加乐观锁)
        boolean success = seckillVoucherService.update().setSql("stock = stock - 1")
                .eq("voucher_id", voucherId)
                // .eq("stock", secKillVoucher.getStock())
                .gt("stock", 0)
                .update();
        if(!success){
            return Result.fail("库存不足！");
        }

        // 7.库存充足，创建订单
        VoucherOrder voucherOrder = new VoucherOrder();
        long orderId = redisIdWorker.getNextId("order");
        voucherOrder.setId(orderId);
        voucherOrder.setVoucherId(voucherId);
        voucherOrder.setUserId(userId);
        save(voucherOrder);

        // 8.返回订单id
        return Result.ok(orderId);
    }
}
