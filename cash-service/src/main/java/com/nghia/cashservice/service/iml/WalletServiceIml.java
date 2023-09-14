package com.nghia.cashservice.service.iml;

import com.google.gson.Gson;
import com.nghia.cashservice.dto.UserResponseDto;
import com.nghia.cashservice.dto.WalletDto;
import com.nghia.cashservice.entity.Transaction;
import com.nghia.cashservice.entity.Wallet;
import com.nghia.cashservice.exception.BaseException;
import com.nghia.cashservice.repository.WalletRepository;
import com.nghia.cashservice.service.TransactionService;
import com.nghia.cashservice.service.WalletService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class WalletServiceIml implements WalletService {

  private final WalletRepository walletRepository;
  private final TransactionService transactionService;
  private final ModelMapper modelMapper;
  private final Gson gson;

  public WalletServiceIml(WalletRepository walletRepository, TransactionService transactionService,
      ModelMapper modelMapper, Gson gson) {
    this.walletRepository = walletRepository;
    this.transactionService = transactionService;
    this.modelMapper = modelMapper;
    this.gson = gson;
  }

  @Override
  public Optional<Wallet> newWallet(UserResponseDto userResponseDto) {
    Wallet wallet = Wallet.builder()
        .balance(0D)
        .userId(userResponseDto.getId())
        .username(userResponseDto.getUsername())
        .createdAt(LocalDateTime.now())
        .build();
    return Optional.of(walletRepository.save(wallet));
  }

  @Override
  @Transactional
  public WalletDto increaseBalance(Transaction transaction) {
    Optional<Wallet> walletOptional = findByUsername(transaction.getUsername());
    if (walletOptional.isEmpty()) {
      throw new BaseException();
    }
    Wallet newWallet = walletOptional.get();
    newWallet.setBalance(newWallet.getBalance() + transaction.getAmount());
    return modelMapper.map(updateWallet(newWallet), WalletDto.class);
  }

  @Override
  @Transactional
  public Wallet updateWallet(Wallet wallet) throws BaseException{
    wallet.setLastUpdateAt(LocalDateTime.now());
    return walletRepository.save(wallet);
  }

  @Override
  public Optional<Wallet> findByUsername(String username) {
    Optional<Wallet> walletOptional =
        walletRepository.findByUsername(username);
    if (walletOptional.isEmpty()) {
      log.warn("FIND WALLET BY USERNAME - REQUEST: {}\n-> FAIL CANNOT FIND WALLET", username);
      return Optional.empty();
    }
    log.info("FIND WALLET BY USERNAME - REQUEST: {}\n-> SUCCESS:\n{}", gson.toJson(username), gson.toJson(walletOptional.orElse(null)));
    return walletOptional;
  }
}
