package com.nghia.cashservice.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DepositMoneyRequest {
  @Valid
  @NotNull(message = "Thiếu order type")
  @NotBlank(message = "Thiếu order type")
  private String orderType;
  @NotNull(message = "Số tiền không được để trống")
  private Long amount;
  private String bankCode;
  private final String language = "vn";
}
