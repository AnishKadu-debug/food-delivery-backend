package com.fooddelivery.food_delivery_backend.config;

import tools.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public RedisCacheManager redisCacheManager(
            RedisConnectionFactory redisConnectionFactory,
            ObjectMapper objectMapper) {

        GenericJacksonJsonRedisSerializer jsonSerializer =
                new GenericJacksonJsonRedisSerializer(objectMapper);

        RedisCacheConfiguration defaultConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .entryTtl(Duration.ofMinutes(10))
                        .disableCachingNullValues()
                        .serializeKeysWith(
                                RedisSerializationContext.SerializationPair
                                        .fromSerializer(new StringRedisSerializer()))
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair
                                        .fromSerializer(jsonSerializer));

        Map<String, RedisCacheConfiguration> cacheConfigurations =
                new HashMap<>();

        cacheConfigurations.put(
                CacheNames.RESTAURANTS,
                defaultConfig.entryTtl(Duration.ofMinutes(5)));

        cacheConfigurations.put(
                CacheNames.RESTAURANT_DETAILS,
                defaultConfig.entryTtl(Duration.ofMinutes(10)));

        cacheConfigurations.put(
                CacheNames.RESTAURANT_SEARCH,
                defaultConfig.entryTtl(Duration.ofMinutes(5)));

        cacheConfigurations.put(
                CacheNames.RESTAURANTS_BY_CITY,
                defaultConfig.entryTtl(Duration.ofMinutes(5)));

        cacheConfigurations.put(
                CacheNames.PENDING_RESTAURANTS,
                defaultConfig.entryTtl(Duration.ofMinutes(5)));

        cacheConfigurations.put(
                CacheNames.MENUS,
                defaultConfig.entryTtl(Duration.ofMinutes(10)));

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(cacheConfigurations)
                .build();
    }
}