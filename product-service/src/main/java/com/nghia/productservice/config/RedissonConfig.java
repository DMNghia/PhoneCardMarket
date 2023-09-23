package com.nghia.productservice.config;

import com.nghia.productservice.common.CodeConstants;
import com.nghia.productservice.common.MessageConstants;
import com.nghia.productservice.common.StatusConstants;
import com.nghia.productservice.dto.request.OrderRequest;
import com.nghia.productservice.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

@Configuration
@Slf4j
public class RedissonConfig {

  @Value("${spring.redis.host}")
  private String REDIS_HOST;

  @Value("${spring.redis.port}")
  private String REDIS_PORT;

  private final String LOCK_NAME = "PURCHASE_LOCK";

  private RedissonClient client() {
    try {
      String address = String.format("redis://%s:%s", REDIS_HOST, REDIS_PORT);
      Config config = new Config();
      config.useClusterServers()
          .addNodeAddress(address);

      return Redisson.create(config);
    } catch (Exception e) {
      log.error("CONFIG FOR REDISSON CLIENT -> FAIL ERROR:", e);
      return null;
    }
  }

  @Bean
  public RBlockingQueue<OrderRequest> rBlockingQueue() {
    RedissonClient redissonClient = client();
    if (ObjectUtils.isEmpty(redissonClient)) {
      throw new BaseException(CodeConstants.ERROR, StatusConstants.ERROR, MessageConstants.ERROR);
    }
    return redissonClient.getBlockingQueue(LOCK_NAME);
  }
}
