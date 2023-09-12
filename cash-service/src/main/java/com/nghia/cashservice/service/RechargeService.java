package com.nghia.cashservice.service;

import com.nghia.cashservice.dto.request.DepositMoneyRequest;
import com.nghia.cashservice.dto.response.BaseResponse;
import javax.servlet.http.HttpServletRequest;

public interface RechargeService {
  public BaseResponse<?> getUrlVnpay(DepositMoneyRequest depositMoneyRequest, HttpServletRequest request);

  public BaseResponse<?> depositMoney(HttpServletRequest request);
}
