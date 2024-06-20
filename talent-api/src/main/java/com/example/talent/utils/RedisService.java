//package com.example.talent.utils;
//
//
//import java.util.concurrent.TimeUnit;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class RedisService {
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    // 设置 key-value 值，带过期时间（单位：秒）
//    public void set(String key, Object value, long timeout) {
//        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
//    }
//
//    // 获取 key 对应的 value 值
//    public Object get(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
//
//    // 删除 key 对应的缓存
//    public void delete(String key) {
//        redisTemplate.delete(key);
//    }
//
//    // 判断 key 是否存在
//    public boolean exists(String key) {
//        return redisTemplate.hasKey(key);
//    }
//
//    // hash 相关操作（可根据业务需要添加更多的方法）
//    private HashOperations<String, String, Object> hashOperations() {
//        return redisTemplate.opsForHash();
//    }
//
//    // 向一个 hash 中存入一个 field-value 对
//    public void hset(String hashKey, String field, Object value) {
//        hashOperations().put(hashKey, field, value);
//    }
//
//    // 从一个 hash 中获取指定 field 的 value
//    public Object hget(String hashKey, String field) {
//        return hashOperations().get(hashKey, field);
//    }
//
//    // 从一个 hash 中删除指定的 field
//    public void hdel(String hashKey, String field) {
//        hashOperations().delete(hashKey, field);
//    }
//}
