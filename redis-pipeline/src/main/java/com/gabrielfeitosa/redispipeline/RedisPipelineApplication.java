package com.gabrielfeitosa.redispipeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@SpringBootApplication
@EnableCaching
public class RedisPipelineApplication implements CommandLineRunner {

    private final static int BATCH_SIZE = 100000;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        var template = new RedisTemplate<String, Object>();
        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.json());
        template.setConnectionFactory(factory);
        return template;
    }

    public static void main(String[] args) {
        SpringApplication.run(RedisPipelineApplication.class, args);
    }

    public void withPipeline() {
        var start = Instant.now();
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            var keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
            var valueSerializer = (RedisSerializer<Object>) redisTemplate.getValueSerializer();
            for (int i = 0; i < BATCH_SIZE; i++) {
                var key = UUID.randomUUID();
                var value = new User(i);
                connection.set(keySerializer.serialize("user::" + key), valueSerializer.serialize(value));
            }
            return null;
        });
        var end = Instant.now();
        System.out.println(getDuration(start, end));
    }

    private long getDuration(Instant start, Instant end) {
        return Duration.between(start, end).getSeconds();
    }

    public void withoutPipeline() {
        var start = Instant.now();
        for (int i = 0; i < BATCH_SIZE; i++) {
            cacheService.save(new User(i));
        }
        var end = Instant.now();
        System.out.println(getDuration(start, end));
    }


    @Override
    public void run(String... args){
//        withoutPipeline();
        withPipeline();
    }
}
