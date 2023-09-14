package com.nghia.cashservice.dto;

import com.nghia.cashservice.entity.StatusTransaction;
import com.nghia.cashservice.entity.TransactionType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentTransactionDto {
  private Double amount;
  private String vnpay_TxnRef;
  private String vnp_TmnCode;
  private Integer userId;
  private String username;
  private StatusTransaction status;
  private TransactionType type;
  private LocalDateTime createdAt;
}
