package com.clx.composite.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 获取 redis连接
 */
public class RedisUtil {
    public static RedisTemplate<Object,Object> redisTemplate;
    public static StringRedisTemplate stringRedisTemplate;

    public  void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    public  StringRedisTemplate getStringRedisTemplate() {
        return stringRedisTemplate;
    }

    public  void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        RedisUtil.stringRedisTemplate = stringRedisTemplate;
    }
}
