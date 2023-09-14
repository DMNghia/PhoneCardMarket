package com.nghia.cashservice.service;

import com.nghia.cashservice.dto.UserResponseDto;
import com.nghia.cashservice.dto.WalletDto;
import com.nghia.cashservice.dto.request.DepositMoneyRequest;
import com.nghia.cashservice.dto.response.PaymentResponse;
import com.nghia.cashservice.entity.Transaction;
import com.nghia.cashservice.entity.Wallet;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

public interface WalletService {
  Optional<Wallet> newWallet(UserResponseDto userResponseDto);
  @Transactional
  WalletDto increaseBalance(Transaction transaction);
  @Transactional
  Wallet updateWallet(Wallet wallet);
  Optional<Wallet> findByUsername(String username);
}
