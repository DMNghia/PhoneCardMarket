package com.nghia.cashservice;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;

@SpringBootApplication
@EnableJpaAuditing
public class CashServiceApplication {

  private final RedisTemplate<String, Object> redisTemplate;

  @Value("${RECHARGE_MIN_LIMIT}")
  private Integer minLimit;
  @Value("${RECHARGE_MAX_LIMIT}")
  private Integer maxLimit;

  public CashServiceApplication(RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public static void main(String[] args) {
    SpringApplication.run(CashServiceApplication.class, args);
  }

  @PostConstruct
  private void createLimitInPayment() {
    redisTemplate.opsForValue().set("RECHARGE_MIN_LIMIT", minLimit);
    redisTemplate.opsForValue().set("RECHARGE_MAX_LIMIT", maxLimit);
  }

}
