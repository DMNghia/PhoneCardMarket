package com.nghia.userservice.service.grpc;

import com.nghia.grpc.entities.cash.CreateWalletRequest;
import com.nghia.grpc.entities.cash.CreateWalletResponse;
import com.nghia.grpc.services.cashService.CashServiceGrpc;
import com.nghia.grpc.services.cashService.CashServiceGrpc.CashServiceBlockingStub;
import com.nghia.grpc.services.userService.UserServiceGrpc;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CashServiceGrpcIml {

  private final CashServiceGrpc.CashServiceBlockingStub stub;
  public CashServiceGrpcIml(@Qualifier("CashServiceChannel")ManagedChannel channel) {
    this.stub = CashServiceGrpc.newBlockingStub(channel);
  }

  public CreateWalletResponse createNewWallet(CreateWalletRequest request) {
    return stub.createWalletByUsername(request);
  }
}
