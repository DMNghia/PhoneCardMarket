package com.nghia.cashservice.service.iml;

import com.nghia.cashservice.dto.UserResponseDto;
import com.nghia.cashservice.entity.Wallet;
import com.nghia.cashservice.repository.WalletRepository;
import com.nghia.cashservice.service.WalletService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WalletServiceIml implements WalletService {

  private final WalletRepository walletRepository;

  public WalletServiceIml(WalletRepository walletRepository) {
    this.walletRepository = walletRepository;
  }

  @Override
  public Optional<Wallet> newWallet(UserResponseDto userResponseDto) {
    Wallet wallet = Wallet.builder()
        .balance(0L)
        .userId(userResponseDto.getId())
        .username(userResponseDto.getUsername())
        .createdAt(LocalDateTime.now())
        .build();
    return Optional.of(walletRepository.save(wallet));
  }
}
