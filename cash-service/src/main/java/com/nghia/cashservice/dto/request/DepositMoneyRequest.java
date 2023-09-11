package com.nghia.cashservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepositMoneyRequest {
  private String orderType;
  private Long amount;
  private String bankCode;
  private final String language = "vn";
}
