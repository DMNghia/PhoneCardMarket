package com.nghia.cashservice.service;

import com.nghia.cashservice.dto.UserResponseDto;
import com.nghia.cashservice.dto.request.DepositMoneyRequest;
import com.nghia.cashservice.dto.response.PaymentResponse;
import com.nghia.cashservice.entity.Wallet;
import java.util.Optional;

public interface WalletService {
  Optional<Wallet> newWallet(UserResponseDto userResponseDto);
  PaymentResponse increaseBalance(DepositMoneyRequest request);
}
