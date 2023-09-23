package com.nghia.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  public final String[] WHITE_LIST = {"/api/provider/upload", "/upload"};

  private final JwtAuthEntryPoint jwtAuthEntryPoint;

  public SecurityConfig(JwtAuthEntryPoint jwtAuthEntryPoint) {
    this.jwtAuthEntryPoint = jwtAuthEntryPoint;
  }

  @Bean
  public JwtFilter jwtFilter() {
    return new JwtFilter();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.cors().and().csrf().disable()
        .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint))
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeRequests(auth -> {
          auth.antMatchers(WHITE_LIST).permitAll();
          auth.anyRequest().authenticated();
        })
        .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
        .build();
  }
}
