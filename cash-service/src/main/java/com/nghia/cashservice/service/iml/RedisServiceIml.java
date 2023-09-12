package com.nghia.cashservice.service.iml;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceIml {

  private final RedisTemplate<String, Object> redisTemplate;
  @Value("${REDIS_HASH_KEY}")
  private String HASH_KEY;

  public RedisServiceIml(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public void putWithTimeout(String key, Object value, long timeout) {
    redisTemplate.opsForHash().put(HASH_KEY, key, value);
    redisTemplate.expire(key, Duration.of(timeout, ChronoUnit.MILLIS));
  }

  public void setWithTimeOut(String key, Object value, long timeout) {
    redisTemplate.opsForValue().set(key, value);
    redisTemplate.expire(key, Duration.of(timeout, ChronoUnit.MILLIS));
  }
}
