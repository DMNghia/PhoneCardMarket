package com.nghia.userservice.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.devh.boot.grpc.server.security.authentication.BasicGrpcAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public Gson gson() {
    return new GsonBuilder().setPrettyPrinting().create();
  }

  @Bean
  public GrpcAuthenticationReader authenticationReader() {
    return new BasicGrpcAuthenticationReader();
  }
}
