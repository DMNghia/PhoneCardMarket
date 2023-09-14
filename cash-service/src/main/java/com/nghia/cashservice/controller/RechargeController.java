package com.nghia.cashservice.controller;

import com.nghia.cashservice.dto.request.DepositMoneyRequest;
import com.nghia.cashservice.dto.response.BaseResponse;
import com.nghia.cashservice.service.RechargeService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class RechargeController {

  private final RechargeService rechargeService;

  public RechargeController(RechargeService rechargeService) {
    this.rechargeService = rechargeService;
  }

  @GetMapping("/deposit")
  public ResponseEntity<?> deposit(
      @RequestParam("vnp_ResponseCode") String responseCode,
      @RequestParam("vnp_SecureHash") String secureHash) {

    return null;
  }

  @PostMapping("/getUrl")
  public ResponseEntity<?> getUrl(@Valid @RequestBody DepositMoneyRequest depositMoneyRequest,
      HttpServletRequest request) {
    BaseResponse<?> response = rechargeService.getUrlVnpay(depositMoneyRequest, request);
    return ResponseEntity.ok(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> exceptionHandler(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(e -> {
      String field = ((FieldError) e).getField();
      String message = e.getDefaultMessage();
      errors.put(field, message);
    });

    return errors;
  }
}
