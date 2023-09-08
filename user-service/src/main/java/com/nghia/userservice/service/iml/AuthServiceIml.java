package com.nghia.userservice.service.iml;

import com.google.gson.Gson;
import com.nghia.userservice.common.CodeConstant;
import com.nghia.userservice.common.ResponseType;
import com.nghia.userservice.dto.MessageDTO;
import com.nghia.userservice.dto.RefreshTokenDTO;
import com.nghia.userservice.dto.request.LoginRequest;
import com.nghia.userservice.dto.request.RefreshTokenRequest;
import com.nghia.userservice.dto.request.SignUpRequest;
import com.nghia.userservice.dto.response.AuthResponse;
import com.nghia.userservice.dto.response.BaseResponse;
import com.nghia.userservice.dto.response.ResponseInfo;
import com.nghia.userservice.entity.ActiveToken;
import com.nghia.userservice.entity.User;
import com.nghia.userservice.exception.MessageException;
import com.nghia.userservice.repository.ActiveTokenRepository;
import com.nghia.userservice.repository.UserRepository;
import com.nghia.userservice.security.JwtUtils;
import com.nghia.userservice.service.ActiveTokenService;
import com.nghia.userservice.service.AuthService;
import com.nghia.userservice.service.MessageService;
import com.nghia.userservice.service.RefreshTokenService;
import com.nghia.userservice.service.UserService;
import com.nghia.userservice.service.kafka.producer.ProducerService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceIml implements AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final UserRepository userRepository;
  private final RefreshTokenService refreshTokenService;
  private final UserService userService;
  private final Gson gson;
  private final ActiveTokenService activeTokenService;
  private final MessageService messageService;
  private final ProducerService producerService;
  private final ActiveTokenRepository activeTokenRepository;

  public AuthServiceIml(AuthenticationManager authenticationManager, JwtUtils jwtUtils,
      UserRepository userRepository, RefreshTokenService refreshTokenService,
      UserService userService, Gson gson, ActiveTokenService activeTokenService,
      MessageService messageService, ProducerService producerService,
      ActiveTokenRepository activeTokenRepository) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
    this.gson = gson;
    this.userRepository = userRepository;
    this.refreshTokenService = refreshTokenService;
    this.userService = userService;
    this.activeTokenService = activeTokenService;
    this.messageService = messageService;
    this.producerService = producerService;
    this.activeTokenRepository = activeTokenRepository;
  }

  @Override
  public BaseResponse<?> login(LoginRequest request) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getUsername(),
              request.getPassword()
          )
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
      String token = jwtUtils.generateToken(authentication.getName());
      log.info("REQUEST LOGIN - {} -> JWT TOKEN - {}", gson.toJson(request), token);
      RefreshTokenDTO tokenDTO = refreshTokenService.createRefreshToken(request.getUsername());
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.SUCCESS_CODE)
              .status(ResponseType.SUCCESS.name())
              .message("Đăng nhập thành công")
              .build())
          .content(AuthResponse.builder()
              .accessToken(token)
              .refreshToken(tokenDTO.getRefreshToken())
              .tokenType("Bearer")
              .build())
          .build();
    } catch (DisabledException de) {
      log.error("REQUEST LOGIN - {} -> FAIL USER IS NOT ENABLED", gson.toJson(request));
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.ACTIVE_CODE)
              .status(ResponseType.ERROR.name())
              .message("Tài khoản chưa được kích hoạt vui lòng vào mail và kích hoạt tài khoản của bạn")
              .build())
          .build();
    } catch (AuthenticationException ae) {
      log.error("REQUEST LOGIN - {} -> FAIL CREDENTIALS NOT CORRECT - {}", gson.toJson(request), ae);
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.ERROR_CODE)
              .status(ResponseType.ERROR.name())
              .message("Tài khoản hoặc mật khẩu không đúng")
              .build())
          .build();
    } catch (Exception e) {
      log.error("REQUEST LOGIN - {} FAIL -> ERROR", request, e);
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.ERROR_CODE)
              .status(ResponseType.ERROR.name())
              .message("Có lỗi xảy ra")
              .build())
          .build();
    }
  }

  @Override
  public BaseResponse<?> signup(SignUpRequest request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      log.info("REQUEST - {} -> FAIL USER EXIST", gson.toJson(request));
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.INVALID_REQUEST_CODE)
              .status(ResponseType.INVALID_REQUEST.name())
              .message("Tên tài khoản đã được sử dụng")
              .build())
          .build();
    }
    if (userRepository.existsByEmail(request.getEmail())) {
      log.info("REQUEST - {} -> FAIL EMAIL EXIST", gson.toJson(request));
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.INVALID_REQUEST_CODE)
              .status(ResponseType.INVALID_REQUEST.name())
              .message("Email đã được sử dụng")
              .build())
          .build();
    }
    User user = userService.register(request).orElseThrow(RuntimeException::new);
    return BaseResponse.builder()
        .responseInfo(ResponseInfo.builder()
            .code(CodeConstant.SUCCESS_CODE)
            .status(ResponseType.SUCCESS.name())
            .message("Đăng ký thành công vui lòng vào mail của bạn và kích hoạt tài khoản")
            .build())
        .build();
  }

  @Override
  public BaseResponse<?> refreshToken(RefreshTokenRequest request) {
    AuthResponse authResponse = refreshTokenService.refreshToken(request.getRefreshToken());
    return BaseResponse.builder()
        .responseInfo(ResponseInfo.builder()
            .code(CodeConstant.SUCCESS_CODE)
            .status(ResponseType.SUCCESS.name())
            .message("Thành công")
            .build())
        .content(authResponse)
        .build();
  }

  @Override
  public BaseResponse<?> activeAccount(String username, String token) {
    Optional<User> userOptional = userService.findByUsername(username);
    if (userOptional.isEmpty()) {
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.INVALID_REQUEST_CODE)
              .status(ResponseType.INVALID_REQUEST.name())
              .message("Tên đăng nhập không tồn tại")
              .build())
          .build();
    }

    User user = userOptional.get();
    Optional<ActiveToken> activeTokenOptional = activeTokenService.findByToken(token);
    if (activeTokenOptional.isEmpty()) {
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.ERROR_CODE)
              .status(ResponseType.ERROR.name())
              .message("Mã kích hoạt không tồn tại")
              .build())
          .build();
    }
    ActiveToken activeToken = activeTokenOptional.get();
    if (activeToken.isUsed()) {
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.ERROR_CODE)
              .status(ResponseType.ERROR.name())
              .message("Mã kích hoạt đã được sử dụng")
              .build())
          .build();
    }
    if (activeToken.getExpiredAt().isBefore(LocalDateTime.now())) {
      ActiveToken newActiveToken = activeTokenService.newActiveToken(user);
      MessageDTO messageDTO = messageService.messageActive(user, newActiveToken.getToken())
          .orElseThrow(() -> new MessageException("Error null"));
      producerService.send(messageDTO);
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.ERROR_CODE)
              .status(ResponseType.ERROR.name())
              .message("Mã kích hoạt đã quá hạn sử dụng chúng tôi đã gửi một mã kích hoạt mới vui lòng kích hoạt lại")
              .build())
          .build();
    }
    activeToken.setUsed(true);
    activeTokenRepository.save(activeToken);
    user.setEnable(true);
    userRepository.save(user);
    return BaseResponse.builder()
        .responseInfo(ResponseInfo.builder()
            .code(CodeConstant.SUCCESS_CODE)
            .status(ResponseType.SUCCESS.name())
            .message("Kích hoạt tài khoản thành công")
            .build())
        .build();
  }
}
