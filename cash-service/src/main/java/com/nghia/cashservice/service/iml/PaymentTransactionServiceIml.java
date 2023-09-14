package com.nghia.cashservice.service.iml;

import com.google.gson.Gson;
import com.nghia.cashservice.dto.PaymentTransactionDto;
import com.nghia.cashservice.dto.request.DepositMoneyRequest;
import com.nghia.cashservice.entity.PaymentTransaction;
import com.nghia.cashservice.repository.PaymentTransactionRepository;
import com.nghia.cashservice.service.PaymentTransactionService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
public class PaymentTransactionServiceIml implements PaymentTransactionService {

  private final Gson gson;
  private final ModelMapper modelMapper;
  private final PaymentTransactionRepository paymentTransactionRepository;

  public PaymentTransactionServiceIml(Gson gson, ModelMapper modelMapper,
      PaymentTransactionRepository paymentTransactionRepository) {
    this.gson = gson;
    this.modelMapper = modelMapper;
    this.paymentTransactionRepository = paymentTransactionRepository;
  }

  @Override
  public Optional<PaymentTransactionDto> createTransaction(PaymentTransactionDto request) {
    log.info("CREATE PAYMENT TRANSACTION REQUEST:\n{}", gson.toJson(request));
    return Optional.ofNullable(modelMapper.map(
        paymentTransactionRepository.save(
            modelMapper.map(request, PaymentTransaction.class)),
        PaymentTransactionDto.class));
  }

  @Override
  public Optional<PaymentTransactionDto> findPaymentTransaction(DepositMoneyRequest request) {
    PaymentTransaction resultOptional =
        paymentTransactionRepository.findByVnpTxnRefAndVnpTmnCode(request.getVnp_TxnRef(),
            request.getVnp_TmnCode());
    if (ObjectUtils.isEmpty(resultOptional)) {
      log.info("FIND PAYMENT PAYMENT  REQUEST:\n{}\n-> FAIL CANNOT FIND", gson.toJson(request));
      return Optional.empty();
    }
    return Optional.empty();
  }

  @Override
  public Optional<PaymentTransaction> updateTransaction(
      PaymentTransactionDto paymentTransactionDto) {
    PaymentTransaction paymentTransaction =
        paymentTransactionRepository.save(modelMapper.map(paymentTransactionDto, PaymentTransaction.class));
    return Optional.ofNullable(paymentTransaction);
  }
}
