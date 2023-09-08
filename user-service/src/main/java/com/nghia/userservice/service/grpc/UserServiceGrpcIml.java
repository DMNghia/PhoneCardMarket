package com.nghia.userservice.service.grpc;

import com.google.gson.Gson;
import com.nghia.grpc.entities.user.BaseResponse;
import com.nghia.grpc.entities.user.FindUserByIdRequest;
import com.nghia.grpc.entities.user.FindUserByUsernameRequest;
import com.nghia.grpc.entities.user.FindUserResponse;
import com.nghia.grpc.entities.user.Role;
import com.nghia.grpc.services.userService.UserServiceGrpc.UserServiceImplBase;
import com.nghia.userservice.common.CodeConstant;
import com.nghia.userservice.common.ResponseType;
import com.nghia.userservice.entity.User;
import com.nghia.userservice.service.UserService;
import io.grpc.stub.StreamObserver;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@Slf4j
public class UserServiceGrpcIml extends UserServiceImplBase {

  private final UserService userService;
  private final Gson gson;

  public UserServiceGrpcIml(UserService userService, Gson gson) {
    this.userService = userService;
    this.gson = gson;
  }

  @Override
  public void findUserById(FindUserByIdRequest request,
      StreamObserver<FindUserResponse> responseObserver) {
    Optional<User> userOptional = userService.findById(request.getId());
    if (userOptional.isEmpty()) {
      log.info("REQUEST FIND BY ID GRPC:\n{}\b-> ERROR USER NOT FOUND" ,gson.toJson(request));
      responseObserver.onNext(FindUserResponse.newBuilder()
              .setBaseResponse(BaseResponse.newBuilder()
                  .setCode(CodeConstant.ERROR_CODE)
                  .setStatus(ResponseType.ERROR.name())
                  .setMessage("User không tìm thấy")
                  .build())
          .build());
      responseObserver.onCompleted();
      return;
    }
    User user = userOptional.get();
    Role role = null;
    switch (user.getRole().getName()) {
      case USER: role = Role.USER;break;
      case ADMIN: role = Role.ADMIN;break;
      case STAFF: role = Role.STAFF;break;
    }
    log.info("REQUEST FIND BY ID GRPC:\n{}\b-> SUCCESS:\n{}", gson.toJson(request), gson.toJson(user));
    responseObserver.onNext(FindUserResponse.newBuilder()
        .setBaseResponse(BaseResponse.newBuilder()
            .setCode(CodeConstant.SUCCESS_CODE)
            .setStatus(ResponseType.SUCCESS.name())
            .setMessage("Thành công")
            .build())
            .setId(user.getId())
            .setUsername(user.getUsername())
            .setEmail(user.getEmail())
            .setIsEnable(user.isEnable())
            .setIsLocked(user.isLocked())
            .setRole(role)
        .build());
    responseObserver.onCompleted();
  }

  @Override
  public void findUserByUsername(FindUserByUsernameRequest request,
      StreamObserver<FindUserResponse> responseObserver) {
    Optional<User> userOptional = userService.findByUsername(request.getUsername());
    if (userOptional.isEmpty()) {
      log.info("REQUEST FIND USER BY USERNAME:\n{}\n-> USER NOT FOUND", gson.toJson(request));
      responseObserver.onNext(FindUserResponse.newBuilder()
          .setBaseResponse(BaseResponse.newBuilder()
              .setCode(CodeConstant.ERROR_CODE)
              .setStatus(ResponseType.ERROR.name())
              .setMessage("User không tìm thấy")
              .build())
          .build());
      responseObserver.onCompleted();
      return;
    }
    User user = userOptional.get();
    Role role = null;
    switch (user.getRole().getName()) {
      case USER: role = Role.USER;break;
      case ADMIN: role = Role.ADMIN;break;
      case STAFF: role = Role.STAFF;break;
    }
    log.info("REQUEST FIND USER BY USERNAME GRPC:\n{}\b-> SUCCESS:\n{}", gson.toJson(request), gson.toJson(user));
    responseObserver.onNext(FindUserResponse.newBuilder()
        .setBaseResponse(BaseResponse.newBuilder()
            .setCode(CodeConstant.SUCCESS_CODE)
            .setStatus(ResponseType.SUCCESS.name())
            .setMessage("Thành công")
            .build())
        .setId(user.getId())
        .setUsername(user.getUsername())
        .setEmail(user.getEmail())
        .setIsEnable(user.isEnable())
        .setIsLocked(user.isLocked())
        .setRole(role)
        .build());
    responseObserver.onCompleted();
  }
}
