package com.wanghuan.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * string get方法
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Object cache = redisTemplate.opsForValue().get(key);
        return cache == null ? null : cache.toString();
    }

    /**
     * string set方法
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * hash 的 put方法
     *
     * @param redisKey
     * @param key
     * @param value
     */
    public void hset(String redisKey, String key, String value) {
        redisTemplate.opsForHash().put(redisKey, key, value);
    }

    public void hincry(String redisKey, String key, Long value) {
        redisTemplate.opsForHash().increment(redisKey, key, value);
    }

    public void hdel(String redisKey, String key) {
        if (StringUtils.isNotBlank(redisKey)) {
            if (StringUtils.isNotBlank(key)) {
                redisTemplate.opsForHash().delete(redisKey, key);
            }
        }
    }

    /**
     * hash 的get方法
     *
     * @param redisKey
     * @param key
     * @return
     */
    public String hget(String redisKey, String key) {
        Object cache = redisTemplate.opsForHash().get(redisKey, key);
        return cache == null ? null : cache.toString();
    }

    /**
     * hash 的entries 方法
     *
     * @param redisKey
     * @return
     */
    public Map hgetAll(String redisKey) {
        Map map = redisTemplate.opsForHash().entries(redisKey);
        return map;
    }

}
