package com.nghia.cashservice.service.grpc.userService;

import com.nghia.grpc.entities.user.FindUserByUsernameRequest;
import com.nghia.grpc.entities.user.FindUserResponse;
import com.nghia.grpc.services.userService.UserServiceGrpc;
import io.grpc.ManagedChannel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceGrpcIml {

  private final ManagedChannel managedChannel;
  private UserServiceGrpc.UserServiceBlockingStub stub;

  public UserServiceGrpcIml(@Qualifier("UserServiceChannel") ManagedChannel managedChannel) {
    this.managedChannel = managedChannel;
    stub = UserServiceGrpc.newBlockingStub(managedChannel);
  }

  public FindUserResponse findUserById(FindUserByUsernameRequest request) {
    FindUserResponse response = stub.findUserByUsername(request);
    return response;
  }
}
