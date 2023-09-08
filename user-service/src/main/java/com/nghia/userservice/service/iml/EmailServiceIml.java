package com.nghia.userservice.service.iml;

import com.nghia.userservice.dto.MessageDTO;
import com.nghia.userservice.service.EmailService;
import javax.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailServiceIml implements EmailService {

  private final JavaMailSender javaMailSender;

  public EmailServiceIml(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Override
  public void sendSimpleMessage(MessageDTO messageDTO) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setTo(messageDTO.getTo());
    simpleMailMessage.setSubject(messageDTO.getSubject());
    simpleMailMessage.setText(messageDTO.getContent());

    log.info("START... Sending email");
    javaMailSender.send(simpleMailMessage);
    log.info("END...SEND MAIL SUCCESS");
  }
}
