package com.nghia.cashservice.controller;

import com.nghia.cashservice.dto.request.DepositMoneyRequest;
import com.nghia.cashservice.dto.response.BaseResponse;
import com.nghia.cashservice.service.RechargeService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RechargeController {

  private final RechargeService rechargeService;

  public RechargeController(RechargeService rechargeService) {
    this.rechargeService = rechargeService;
  }

  @PostMapping("/payment/getUrl")
  public ResponseEntity<?> getUrl(@RequestBody DepositMoneyRequest depositMoneyRequest,
      HttpServletRequest request) {
    BaseResponse<?> response = rechargeService.getUrlVnpay(depositMoneyRequest, request);
    return ResponseEntity.ok(response);
  }
}
