package com.gabrielfeitosa.redispipeline;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Cacheable(cacheNames = "user", key = "#user.id")
    public User save(User user) {
        return user;
    }

    @Cacheable(cacheNames = "user", key = "#key")
    public Object get(String key) {
        throw new RuntimeException();
    }

}
