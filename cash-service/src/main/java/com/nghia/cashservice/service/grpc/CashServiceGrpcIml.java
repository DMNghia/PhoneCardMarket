package com.nghia.cashservice.service.grpc;

import com.google.gson.Gson;
import com.nghia.cashservice.common.CodeConstant;
import com.nghia.cashservice.common.ResponseType;
import com.nghia.cashservice.dto.UserResponseDto;
import com.nghia.cashservice.entity.Wallet;
import com.nghia.cashservice.service.WalletService;
import com.nghia.cashservice.service.grpc.userService.UserServiceGrpcIml;
import com.nghia.grpc.entities.cash.BaseResponse;
import com.nghia.grpc.entities.cash.CreateWalletRequest;
import com.nghia.grpc.entities.cash.CreateWalletResponse;
import com.nghia.grpc.entities.user.FindUserByUsernameRequest;
import com.nghia.grpc.entities.user.FindUserResponse;
import com.nghia.grpc.services.cashService.CashServiceGrpc;
import io.grpc.stub.StreamObserver;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CashServiceGrpcIml extends CashServiceGrpc.CashServiceImplBase {

  private final UserServiceGrpcIml userServiceGrpcIml;
  private final WalletService walletService;

  private final Gson gson;

  public CashServiceGrpcIml(UserServiceGrpcIml userServiceGrpcIml, WalletService walletService,
      Gson gson) {
    this.userServiceGrpcIml = userServiceGrpcIml;
    this.walletService = walletService;
    this.gson = gson;
  }

  @Override
  public void createWalletByUsername(CreateWalletRequest request,
      StreamObserver<CreateWalletResponse> responseObserver) {
    FindUserResponse userResponse = userServiceGrpcIml.findUserByUsername(
        FindUserByUsernameRequest.newBuilder()
            .setUsername(request.getUsername())
            .build());

    if (!userResponse.getBaseResponse().getCode().equals("00")) {
      log.error("CREATE WALLET REQUEST - {} -> FAIL\n{}", gson.toJson(request),
          gson.toJson(userResponse));
      responseObserver.onNext(CreateWalletResponse.newBuilder()
          .setResponseInfo(BaseResponse.newBuilder()
              .setCode(userResponse.getBaseResponse().getCode())
              .setStatus(userResponse.getBaseResponse().getStatus())
              .setMessage(userResponse.getBaseResponse().getMessage())
              .build())
          .build());
      responseObserver.onCompleted();
      return;
    }
    try {
      UserResponseDto userResponseDto = UserResponseDto.builder()
          .id(userResponse.getId())
          .username(userResponse.getUsername())
          .email(userResponse.getEmail())
          .isEnable(userResponse.getIsEnable())
          .isLocked(userResponse.getIsLocked())
          .build();
      Optional<Wallet> newWallet = walletService.newWallet(userResponseDto);

      if (newWallet.isPresent()) {
        CreateWalletResponse response = CreateWalletResponse.newBuilder()
            .setResponseInfo(BaseResponse.newBuilder()
                .setCode(CodeConstant.SUCCESS_CODE)
                .setStatus(ResponseType.SUCCESS.name())
                .setMessage("Thành công")
                .build())
            .setId(newWallet.get().getId())
            .setBalance(newWallet.get().getBalance())
            .build();
        log.error("CREATE WALLET REQUEST - {} -> SUCCESS\n", gson.toJson(request),
            gson.toJson(response));
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        return;
      }
      CreateWalletResponse response = CreateWalletResponse.newBuilder()
          .setResponseInfo(BaseResponse.newBuilder()
              .setCode(CodeConstant.ERROR_CODE)
              .setStatus(ResponseType.ERROR.name())
              .setMessage("Không thành công")
              .build())
          .build();
      log.error("CREATE WALLET REQUEST - {} -> FAIL\n", gson.toJson(request),
          gson.toJson(newWallet));
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    } catch (RuntimeException re) {
      log.error("CREATE WALLET REQUEST - {} -> FAIL", gson.toJson(request), re);
      responseObserver.onNext(CreateWalletResponse.newBuilder()
          .setResponseInfo(BaseResponse.newBuilder()
              .setCode(CodeConstant.ERROR_CODE)
              .setStatus(ResponseType.ERROR.name())
              .setMessage(re.getMessage())
              .build())
          .build());
      responseObserver.onCompleted();
    }
  }
}
