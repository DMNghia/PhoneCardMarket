package com.nghia.userservice.exception;

import com.nghia.userservice.dto.MessageDTO;

public class MessageException extends RuntimeException{
  public MessageException(String message) {
    super("Exception - " + message);
  }
}
