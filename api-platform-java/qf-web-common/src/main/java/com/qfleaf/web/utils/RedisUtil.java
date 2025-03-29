package com.qfleaf.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;

    private ValueOperations<String, Object> valueOps;
    private HashOperations<String, String, Object> hashOps;
    private ListOperations<String, Object> listOps;
    private SetOperations<String, Object> setOps;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        init();
    }

    public void init() {
        this.valueOps = redisTemplate.opsForValue();
        this.hashOps = redisTemplate.opsForHash();
        this.listOps = redisTemplate.opsForList();
        this.setOps = redisTemplate.opsForSet();
    }

    // ==================== String 操作 ====================

    public void set(String key, Object value) {
        valueOps.set(key, value);
    }

    public void setWithExpiration(String key, Object value, long timeout, TimeUnit timeUnit) {
        valueOps.set(key, value, timeout, timeUnit);
    }

    public Object get(String key) {
        return valueOps.get(key);
    }

    public Boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    // ==================== Hash 操作 ====================

    public void putHash(String hashKey, String key, Object value) {
        hashOps.put(hashKey, key, value);
    }

    public Object getHash(String hashKey, String key) {
        return hashOps.get(hashKey, key);
    }

    public void deleteHash(String hashKey, String key) {
        hashOps.delete(hashKey, key);
    }

    public Boolean existsInHash(String hashKey, String key) {
        return hashOps.hasKey(hashKey, key);
    }

    // ==================== List 操作 ====================

    public void leftPush(String key, Object value) {
        listOps.leftPush(key, value);
    }

    public void rightPush(String key, Object value) {
        listOps.rightPush(key, value);
    }

    public List<Object> range(String key, long start, long end) {
        return listOps.range(key, start, end);
    }

    public Long sizeOfList(String key) {
        return listOps.size(key);
    }

    public void removeFromList(String key, Object value) {
        listOps.remove(key, 0, value);
    }

    // ==================== Set 操作 ====================

    public void addToSet(String key, Object value) {
        setOps.add(key, value);
    }

    public Set<Object> getSet(String key) {
        return setOps.members(key);
    }

    public Boolean isMemberOfSet(String key, Object value) {
        return setOps.isMember(key, value);
    }

    public Long sizeOfSet(String key) {
        return setOps.size(key);
    }

    public void removeFromSet(String key, Object value) {
        setOps.remove(key, value);
    }

    // ==================== Expiration 操作 ====================

    public Boolean setExpiration(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

    public Long getExpiration(String key) {
        return redisTemplate.getExpire(key);
    }
}