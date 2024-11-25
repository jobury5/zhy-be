package com.zhy.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public interface RedisService {

    Boolean set(String key, Object value);

    Boolean set(String key, Object value, long time, TimeUnit timeUnit);

    Object get(String key);

    void delete(String key);

    Boolean expire(String key, long time, TimeUnit timeUnit);

    Boolean hasKey(String key);

    Object hget(String key, String item);

    Map<Object, Object> hmget(String key);

    Boolean hmset(String key, Map<String, Object> map);

    Boolean hmset(String key, Map<String, Object> map, long time, TimeUnit timeUnit);

    Boolean hset(String key, String item, Object value);

    Boolean hset(String key, String item, Object value, long time, TimeUnit timeUnit);

    void hdel(String key, Object... item);

    Boolean hHasKey(String key, String item);
}
