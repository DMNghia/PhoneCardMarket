package com.nghia.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  public final String[] WHITE_LIST = {"/api/provider/upload", "/upload"};

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.cors().and().csrf().disable()
        .authorizeRequests(auth -> {
          auth.antMatchers(WHITE_LIST).permitAll();
          auth.anyRequest().authenticated();
        })
        .build();
  }
}
