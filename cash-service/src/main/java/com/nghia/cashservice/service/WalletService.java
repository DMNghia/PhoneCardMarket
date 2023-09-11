package com.nghia.cashservice.service;

import com.nghia.cashservice.dto.UserResponseDto;
import com.nghia.cashservice.entity.Wallet;
import java.util.Optional;

public interface WalletService {
  Optional<Wallet> newWallet(UserResponseDto userResponseDto);
}
