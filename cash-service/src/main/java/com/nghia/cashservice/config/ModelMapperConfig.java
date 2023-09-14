package com.nghia.cashservice.config;

import com.nghia.cashservice.dto.PaymentTransactionDto;
import com.nghia.cashservice.dto.UserResponseDto;
import com.nghia.cashservice.dto.WalletDto;
import com.nghia.cashservice.dto.response.ResponseInfo;
import com.nghia.cashservice.entity.PaymentTransaction;
import com.nghia.cashservice.entity.Wallet;
import com.nghia.grpc.entities.user.FindUserResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
        .setMatchingStrategy(MatchingStrategies.STANDARD);

    // Map PaymentTransaction to its dto
    modelMapper.addMappings(new PropertyMap<PaymentTransaction, PaymentTransactionDto>() {
      @Override
      protected void configure() {
        map().setAmount(source.getAmount());
        map().setVnpaySecureHash(source.getVnpaySecureHash());
        map().setUsername(source.getUsername());
        map().setUserId(source.getUserId());
        map().setStatus(source.getStatus());
        map().setType(source.getType());
        map().setCreatedAt(source.getCreatedAt());
      }
    });

    // Map dto to PaymentTransaction
    modelMapper.addMappings(new PropertyMap<PaymentTransactionDto, PaymentTransaction>() {
      @Override
      protected void configure() {
        map().setAmount(source.getAmount());
        map().setVnpaySecureHash(source.getVnpaySecureHash());
        map().setUsername(source.getUsername());
        map().setUserId(source.getUserId());
        map().setStatus(source.getStatus());
        map().setType(source.getType());
        map().setCreatedAt(source.getCreatedAt());
      }
    });

    // Wallet -> WalletDto
    modelMapper.addMappings(new PropertyMap<Wallet, WalletDto>() {
      @Override
      protected void configure() {
        map().setBalance(source.getBalance());
        map().setUsername(source.getUsername());
        map().setUserId(source.getUserId());
        map().setCreatedAt(source.getCreatedAt());
        map().setLastUpdateAt(source.getLastUpdateAt());
      }
    });

    // WalletDto -> Wallet
    modelMapper.addMappings(new PropertyMap<WalletDto, Wallet>() {
      @Override
      protected void configure() {
        map().setBalance(source.getBalance());
        map().setUsername(source.getUsername());
        map().setUserId(source.getUserId());
        map().setCreatedAt(source.getCreatedAt());
        map().setLastUpdateAt(source.getLastUpdateAt());
      }
    });

    // PaymentTransactionDto -> PaymentTransaction
    return modelMapper;
  }
}
