package com.nghia.userservice.config.grpc;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CashServiceGrpcClientConfig {

  @Value("${grpc.server.cash-service.host}")
  private String host;
  @Value("${grpc.server.cash-service.port}")
  private Integer port;
  @Value("${grpc.server.cash-service.timeout}")
  private Integer timeout;

  @Bean("CashServiceChannel")
  public ManagedChannel cashServiceChannel() {
    return NettyChannelBuilder.
        forAddress(host, port)
        .usePlaintext()
        .withOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
        .build();
  }
}
