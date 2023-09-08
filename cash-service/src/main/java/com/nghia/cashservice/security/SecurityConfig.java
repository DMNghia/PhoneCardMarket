package com.nghia.cashservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final String[] WHITE_LIST = {"/api/public/**"};

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.cors().and().csrf().disable()
        .authorizeRequests(auth -> {
          auth.antMatchers(WHITE_LIST).permitAll();
          auth.anyRequest().authenticated();
        })
        .oauth2Login(oauth2 -> oauth2.loginPage("/oauth2/authorization/cash-service-client"))
        .oauth2Client(Customizer.withDefaults())
        .build();
  }
}
