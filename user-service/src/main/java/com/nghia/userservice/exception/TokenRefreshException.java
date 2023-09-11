package com.nghia.userservice.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Slf4j
@Getter
@Setter
public class TokenRefreshException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private String message;

  public TokenRefreshException(String token, String message) {
    super(String.format("Failed for [%s]: %s", token, message));
    log.error("-> FAIL REFRESH TOKEN NOT EXIST");
    this.message = message;
  }
}
