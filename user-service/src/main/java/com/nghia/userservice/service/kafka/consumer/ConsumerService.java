package com.nghia.userservice.service.kafka.consumer;

import com.google.gson.Gson;
import com.nghia.userservice.dto.MessageDTO;
import com.nghia.userservice.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.converter.GsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {

  private final EmailService emailService;
  private final Gson gson;

  public ConsumerService(EmailService emailService, Gson gson) {
    this.emailService = emailService;
    this.gson = gson;
  }

  @KafkaListener(id = "mailGroup", topics = "mail")
  public void listen(MessageDTO message) {
    log.info("RECEIVED MESSAGE - {}", gson.toJson(message));
    emailService.sendSimpleMessage(message);
  }
}
