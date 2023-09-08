package com.nghia.userservice.config;

import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

  @Value("${MAIL_USERNAME}")
  private String username;

  @Value("${MAIL_PASSWORD}")
  private String password;

  @Value("${MAIL_HOST}")
  private String host;

  @Value("${MAIL_PORT}")
  private String port;

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    sender.setPort(Integer.parseInt(port));
    sender.setHost(host);
    sender.setUsername(new String(Base64.getDecoder().decode(username)));
    sender.setPassword(new String(Base64.getDecoder().decode(password)));

    Properties properties = sender.getJavaMailProperties();
    properties.put("mail.transport.protocol", "smtp");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.debug", "true");

    return sender;
  }
}
