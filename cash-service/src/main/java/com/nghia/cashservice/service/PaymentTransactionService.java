package com.nghia.cashservice.service;

import com.nghia.cashservice.dto.PaymentTransactionDto;
import com.nghia.cashservice.dto.request.DepositMoneyRequest;
import com.nghia.cashservice.entity.PaymentTransaction;
import java.util.Optional;

public interface PaymentTransactionService {

  Optional<PaymentTransactionDto> createTransaction(PaymentTransactionDto request);
  Optional<PaymentTransactionDto> findPaymentTransaction(DepositMoneyRequest request);
  Optional<PaymentTransaction> updateTransaction(PaymentTransactionDto paymentTransactionDto);
}
