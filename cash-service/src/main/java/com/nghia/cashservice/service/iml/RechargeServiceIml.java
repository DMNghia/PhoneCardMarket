package com.nghia.cashservice.service.iml;

import com.google.gson.Gson;
import com.nghia.cashservice.common.CodeConstant;
import com.nghia.cashservice.common.ResponseType;
import com.nghia.cashservice.config.VNPayConfig;
import com.nghia.cashservice.dto.PaymentTransactionDto;
import com.nghia.cashservice.dto.UserResponseDto;
import com.nghia.cashservice.dto.request.DepositMoneyRequest;
import com.nghia.cashservice.dto.request.RechargeRequest;
import com.nghia.cashservice.dto.response.BaseResponse;
import com.nghia.cashservice.dto.response.PaymentResponse;
import com.nghia.cashservice.dto.response.ResponseInfo;
import com.nghia.cashservice.entity.PaymentTransaction;
import com.nghia.cashservice.entity.StatusTransaction;
import com.nghia.cashservice.entity.Transaction;
import com.nghia.cashservice.entity.TransactionType;
import com.nghia.cashservice.exception.BaseException;
import com.nghia.cashservice.service.PaymentTransactionService;
import com.nghia.cashservice.service.RechargeService;
import com.nghia.cashservice.service.TransactionService;
import com.nghia.cashservice.service.WalletService;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RechargeServiceIml implements RechargeService {

  private final Gson gson;
  private final RedisTemplate<String, Object> redisTemplate;
  private final PaymentTransactionService paymentTransactionService;
  private final WalletService walletService;
  private final TransactionService transactionService;

  public RechargeServiceIml(Gson gson, RedisTemplate<String, Object> redisTemplate,
      PaymentTransactionService paymentTransactionService, WalletService walletService,
      TransactionService transactionService) {
    this.gson = gson;
    this.redisTemplate = redisTemplate;
    this.paymentTransactionService = paymentTransactionService;
    this.walletService = walletService;
    this.transactionService = transactionService;
  }

  @Override
  public BaseResponse<?> getUrlVnpay(RechargeRequest rechargeRequest,
      HttpServletRequest request) {
    try {
      Integer minLimit = (Integer) redisTemplate.opsForValue().get("RECHARGE_MIN_LIMIT");
      Integer maxLimit = (Integer) redisTemplate.opsForValue().get("RECHARGE_MAX_LIMIT");

      if (rechargeRequest.getAmount() > Long.valueOf(maxLimit)) {
        log.info("GET URL VNPAY REQUEST:\n{}\n-> FAIL: MONEY GREATER THAN MAXIMUM RECHARGE: {}",
            gson.toJson(rechargeRequest), maxLimit);
        return BaseResponse.builder()
            .responseInfo(ResponseInfo.builder()
                .code(CodeConstant.INVALID_REQUEST_CODE)
                .status(ResponseType.INVALID_REQUEST.name())
                .message("Số tiền vượt quá giới hạn cho phép, tối đa 20,000,000 đồng")
                .build())
            .content(null)
            .build();
      }

      if (rechargeRequest.getAmount() < Long.valueOf(minLimit)) {
        log.info("GET URL VNPAY REQUEST:\n{}\n-> FAIL: MONEY LESS THAN MINIMUM RECHARGE: {}",
            gson.toJson(rechargeRequest), minLimit);
        return BaseResponse.builder()
            .responseInfo(ResponseInfo.builder()
                .code(CodeConstant.INVALID_REQUEST_CODE)
                .status(ResponseType.INVALID_REQUEST.name())
                .message("Số tiền tối thiều là 5000 đồng")
                .build())
            .content(null)
            .build();
      }
    } catch (Exception e) {
      log.error("GET URL VNPAY REQUEST:\n{}\n-> ERROR:", gson.toJson(rechargeRequest), e);
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.ERROR_CODE)
              .status(ResponseType.ERROR.name())
              .message("Có lỗi xảy ra")
              .build())
          .content(null)
          .build();
    }

    String orderType = rechargeRequest.getOrderType();
    long amount = (long) (rechargeRequest.getAmount() * 100);
    String bankCode = rechargeRequest.getBankCode();

    String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
    String vnp_IpAddr = VNPayConfig.getIpAddress(request);
    String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

    Map<String, String> vnp_Params = new HashMap<>();
    vnp_Params.put("vnp_Version", VNPayConfig.vnp_Version);
    vnp_Params.put("vnp_Command", VNPayConfig.vnp_Command);
    vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
    vnp_Params.put("vnp_Amount", String.valueOf(amount));
    vnp_Params.put("vnp_CurrCode", "VND");

    if (bankCode != null && !bankCode.isEmpty()) {
      vnp_Params.put("vnp_BankCode", bankCode);
    }
    vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
    vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
    vnp_Params.put("vnp_OrderType", orderType);

    String locate = rechargeRequest.getLanguage();
    if (locate != null && !locate.isEmpty()) {
      vnp_Params.put("vnp_Locale", locate);
    } else {
      vnp_Params.put("vnp_Locale", "vn");
    }
    vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_Returnurl);
    vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

    Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    String vnp_CreateDate = formatter.format(cld.getTime());
    vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

    cld.add(Calendar.MINUTE, 15);
    String vnp_ExpireDate = formatter.format(cld.getTime());
    vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

    List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
    Collections.sort(fieldNames);
    StringBuilder hashData = new StringBuilder();
    StringBuilder query = new StringBuilder();
    Iterator<String> itr = fieldNames.iterator();
    while (itr.hasNext()) {
      String fieldName = itr.next();
      String fieldValue = vnp_Params.get(fieldName);
      if ((fieldValue != null) && (!fieldValue.isEmpty())) {
        //Build hash data
        hashData.append(fieldName);
        hashData.append('=');
        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
        //Build query
        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
        query.append('=');
        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
        if (itr.hasNext()) {
          query.append('&');
          hashData.append('&');
        }
      }
    }
    String queryUrl = query.toString();
    String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.vnp_HashSecret, hashData.toString());
    queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
    String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;

    log.info("VNPAY PARAM:\n{}", gson.toJson(vnp_Params));
    log.info("paymentURL: {}", paymentUrl);

    BaseResponse<Object> response = BaseResponse.builder()
        .responseInfo(ResponseInfo.builder()
            .code(CodeConstant.SUCCESS_CODE)
            .status(ResponseType.SUCCESS.name())
            .message("Thành công")
            .build())
        .content(PaymentResponse.builder()
            .data(paymentUrl)
            .build())
        .build();
    log.info("GET VNPAY URL REQUEST:\n{}-> SUCCESS:\n{}", gson.toJson(rechargeRequest),
        gson.toJson(response));

    // Get authentication
    UserResponseDto userResponseDto =
        (UserResponseDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    // Create request create payment transaction
    PaymentTransactionDto paymentTransactionDto = PaymentTransactionDto.builder()
        .vnp_TmnCode(vnp_TmnCode)
        .vnpay_TxnRef(vnp_TxnRef)
        .userId(userResponseDto.getId())
        .username(userResponseDto.getUsername())
        .amount(rechargeRequest.getAmount())
        .status(StatusTransaction.PENDING)
        .type(TransactionType.IN)
        .build();

    paymentTransactionService.createTransaction(paymentTransactionDto);
    return response;
  }

  @Override
  public BaseResponse<?> depositMoney(DepositMoneyRequest request) {
    Optional<PaymentTransactionDto> paymentTransactionDto =
        paymentTransactionService.findPaymentTransaction(request);
    if (paymentTransactionDto.isEmpty()) {
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.INVALID_REQUEST_CODE)
              .status(ResponseType.INVALID_REQUEST.name())
              .message("Không tìm thấy giao dịch")
              .build())
          .build();
    }
    PaymentTransactionDto paymentTransaction =
        paymentTransactionDto.get();
    if (!paymentTransaction.getStatus().equals(StatusTransaction.PENDING)) {
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.INVALID_REQUEST_CODE)
              .status(ResponseType.INVALID_REQUEST.name())
              .message("Giao dịch đã thực hiện hoàn tất, đã tồn tại")
              .build())
          .build();
    }
    if (!request.getResponseCode().equals("00") || !request.getVnp_TransactionStatus().equals("00")) {
      paymentTransaction.setStatus(StatusTransaction.FAIL);
      paymentTransactionService.updateTransaction(paymentTransaction);
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.ERROR_CODE)
              .status(ResponseType.ERROR.name())
              .message("Giao dịch lỗi thực hiện không thành công")
              .build())
          .build();
    }
    paymentTransaction.setStatus(StatusTransaction.SUCCESS);
    Optional<PaymentTransaction> paymentTransactionOptional =
        paymentTransactionService.updateTransaction(paymentTransaction);
    if (paymentTransactionOptional.isEmpty()) {
      throw new BaseException();
    }
    walletService.increaseBalance(transactionService.createTransaction(
        Transaction.builder()
            .amount(paymentTransaction.getAmount())
            .status(paymentTransaction.getStatus())
            .paymentTransaction(paymentTransactionOptional.get())
            .createdAt(paymentTransaction.getCreatedAt())
            .description("Giao dịch nạp tiền")
            .type(paymentTransactionOptional.get().getType())
            .status(paymentTransactionOptional.get().getStatus())
            .userId(paymentTransactionOptional.get().getUserId())
            .username(paymentTransactionOptional.get().getUsername())
            .build()
    ));
    return BaseResponse.builder()
        .responseInfo(ResponseInfo.builder()
            .code(CodeConstant.ERROR_CODE)
            .status(ResponseType.ERROR.name())
            .message("Giao dịch lỗi thực hiện không thành công")
            .build())
        .content(paymentTransactionService.updateTransaction(paymentTransaction).orElse(null))
        .build();
  }
}
