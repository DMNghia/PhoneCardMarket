package com.nghia.userservice.service.kafka.producer;

import com.nghia.userservice.dto.MessageDTO;
import com.nghia.userservice.entity.Message;
import com.nghia.userservice.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProducerService {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final MessageRepository messageRepository;

  public ProducerService(KafkaTemplate<String, Object> kafkaTemplate,
      MessageRepository messageRepository) {
    this.kafkaTemplate = kafkaTemplate;
    this.messageRepository = messageRepository;
  }

  public void send(MessageDTO messageDTO) {
    Message message = messageRepository.findById(messageDTO.getId()).orElse(null);
    kafkaTemplate.send("notification", messageDTO)
        .addCallback(new KafkaSendCallback<String, Object>() {

          @Override
          public void onSuccess(SendResult<String, Object> result) {
            // handler when success: save db, print...
            log.info("SEND - {} -> SUCCESS", messageDTO);
            message.setSent(true);
            messageRepository.save(message);
          }

          @Override
          public void onFailure(KafkaProducerException ex) {
            // handler when fail: save db, print...
            log.error("SEND - {} -> FAIL - {}",messageDTO, ex);
          }
        });
  }
}
