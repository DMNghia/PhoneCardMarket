package com.nghia.cashservice.service;

import com.nghia.cashservice.dto.request.DepositMoneyRequest;
import com.nghia.cashservice.dto.request.RechargeRequest;
import com.nghia.cashservice.dto.response.BaseResponse;
import javax.servlet.http.HttpServletRequest;

public interface RechargeService {
  public BaseResponse<?> getUrlVnpay(RechargeRequest rechargeRequest, HttpServletRequest request);

  public BaseResponse<?> depositMoney(DepositMoneyRequest request);
}
