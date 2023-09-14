package com.nghia.cashservice.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletDto {
  private Double balance;
  private Integer userId;
  private String username;
  private LocalDateTime createdAt;
  private LocalDateTime lastUpdateAt;
}
