package com.nghia.cashservice.config;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceGrpcClientConfig {

  @Value("${grpc.server.user-service.host}")
  private String host;

  @Value("${grpc.server.user-service.port}")
  private Integer port;

  @Value("${grpc.server.user-service.timeout}")
  private Integer timeout;
  @Bean("UserServiceChannel")
  public ManagedChannel initUserChannel() {
    return NettyChannelBuilder
        .forAddress(host, port)
        .usePlaintext()
        .withOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
        .build();
  }
}
