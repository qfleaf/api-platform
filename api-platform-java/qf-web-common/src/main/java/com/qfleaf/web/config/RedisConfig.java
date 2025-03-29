package com.qfleaf.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());  // 键使用 StringRedisSerializer
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());  // 值使用 JSON 序列化器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());  // Hash 键使用 StringRedisSerializer
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());  // Hash 值使用 JSON 序列化器
        return redisTemplate;
    }
}