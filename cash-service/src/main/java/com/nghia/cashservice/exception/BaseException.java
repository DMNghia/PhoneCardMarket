package com.nghia.cashservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseException extends RuntimeException{

  private String code;
  private String status;
  private String message;

  public BaseException(String message) {
    super(message);
    this.message = message;
  }

}
