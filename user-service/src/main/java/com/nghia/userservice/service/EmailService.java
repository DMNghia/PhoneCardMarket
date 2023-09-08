package com.nghia.userservice.service;

import com.nghia.userservice.dto.MessageDTO;

public interface EmailService {
  void sendSimpleMessage(MessageDTO messageDTO);
}
