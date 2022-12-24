package com.itheima.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class MyRedisConfiguration {
    /**
     * RedisConnectionFactory就是我们在配置文件中定义属性的对象
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory){
        // 创建RedisTemplate对象
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置连接工厂
        template.setConnectionFactory(connectionFactory);
        // 创建JSON序列化工具
        GenericJackson2JsonRedisSerializer jsonRedisSerializer =
                new GenericJackson2JsonRedisSerializer();
        // 设置Key的序列化
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        // 设置Value的序列化
        //GenericJackson2JsonRedisSerializer()的好处在于不需要像Jackson2JsonRedisSerializer<>那样必须定义泛型，这样就可以适配更多类型的数据了。
        //在序列化的对象时，GenericJackson2JsonRedisSerializer会将对象的完整类型名称作为新的属性存放，并在反序列化的时候根据该完整类型名称将json字符串转换成指定对象。
        //比如a.b.User类型的java对象，有属性name和age
        //序列化 -> {"@class": "a.b.User", "name": "xx", "age": 25}
        //反序列化 -> 读取json中的class属性，决定转换为什么类型的对象
        template.setValueSerializer(jsonRedisSerializer);
        template.setHashValueSerializer(jsonRedisSerializer);
        // 返回
        return template;
    }
}