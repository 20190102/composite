package com.clx.composite.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis工具类，方法名对应redis命令行操作
 */
@Component
public class RedisUtil<K, V> {

    @Resource
    private RedisTemplate<K, V> redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public V get(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    public  void set(K key, V value, int timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public  void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void hmset(String key, Map<String, String> map, int timeout) {
        stringRedisTemplate.opsForHash().putAll(key, map);

    }

    public String hget(String key, String field) {
        String value = (String) stringRedisTemplate.opsForHash().get(key, field);
        return value;
    }


    public void expire(String key, int timeout) {
        stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }


    public void rpush(K key, List<V> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    public List<V> lrange(K key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


    public boolean exists(K key) {
        return redisTemplate.hasKey(key);
    }

    public void hset(K key,Object field,Object value){
        redisTemplate.opsForHash().put(key,field,value);
    }
    public Object hget(K key,Object field){
        return redisTemplate.opsForHash().get(key,field);
    }

    public long llen(K key){
        return redisTemplate.opsForList().size(key);
    }
}
