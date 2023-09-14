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

  private String responseCode;
  private String vnp_TransactionStatus;
  private String vnp_TxnRef;
  private String vnp_TmnCode;
}
