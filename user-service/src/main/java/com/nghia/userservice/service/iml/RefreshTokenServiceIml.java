package com.nghia.userservice.service.iml;

import com.google.gson.Gson;
import com.nghia.userservice.common.CodeConstant;
import com.nghia.userservice.common.ResponseType;
import com.nghia.userservice.dto.RefreshTokenDTO;
import com.nghia.userservice.dto.response.AuthResponse;
import com.nghia.userservice.dto.response.BaseResponse;
import com.nghia.userservice.dto.response.ResponseInfo;
import com.nghia.userservice.entity.RefreshToken;
import com.nghia.userservice.entity.User;
import com.nghia.userservice.exception.TokenRefreshException;
import com.nghia.userservice.repository.RefreshTokenRepository;
import com.nghia.userservice.repository.UserRepository;
import com.nghia.userservice.security.JwtUtils;
import com.nghia.userservice.security.SecurityConstant;
import com.nghia.userservice.service.RefreshTokenService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class RefreshTokenServiceIml implements RefreshTokenService {

  private final UserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtUtils jwtUtils;
  private final Gson gson;

  public RefreshTokenServiceIml(UserRepository userRepository,
      RefreshTokenRepository refreshTokenRepository, JwtUtils jwtUtils, Gson gson) {
    this.userRepository = userRepository;
    this.refreshTokenRepository = refreshTokenRepository;
    this.jwtUtils = jwtUtils;
    this.gson = gson;
  }

  @Override
  public RefreshTokenDTO createRefreshToken(String username) {
    RefreshTokenDTO tokenDTO = RefreshTokenDTO.builder()
        .username(username)
        .refreshToken(UUID.randomUUID().toString())
        .expiredAt(LocalDateTime.now().plusSeconds(SecurityConstant.REFRESH_TOKEN_EXPIRATION_SECOND))
        .build();

    Optional<User> user = userRepository.findByUsername(username);
    if (user.isPresent()) {
      RefreshToken refreshToken = new RefreshToken();
      refreshToken.setToken(tokenDTO.getRefreshToken());
      refreshToken.setCreatedAt(LocalDateTime.now());
      refreshToken.setUser(user.get());
      refreshToken.setExpiredAt(tokenDTO.getExpiredAt());

      refreshTokenRepository.save(refreshToken);
    }
    return tokenDTO;
  }

  @Override
  @Transactional
  public boolean verifyExpiration(RefreshTokenDTO tokenDTO) {
    if (tokenDTO.getExpiredAt().isBefore(LocalDateTime.now())) {
      deletedByToken(tokenDTO.getRefreshToken());
      log.info("VERIFY EXPIRATION REQUEST:\n{}\n-> SUCCESS: TOKEN WAS EXPIRED", gson.toJson(tokenDTO));
      return false;
    }
    return true;
  }

  @Override
  @Transactional
  public BaseResponse<?> refreshToken(String refreshTokenRequest) {
    log.info("REFRESH TOKEN REQUEST:\n{}\n", refreshTokenRequest);
    RefreshTokenDTO tokenDTO = refreshTokenRepository.findByToken(refreshTokenRequest)
        .map(token -> RefreshTokenDTO.builder()
            .refreshToken(token.getToken())
            .expiredAt(token.getExpiredAt())
            .username(token.getUser().getUsername())
            .build())
        .orElseThrow(() -> new TokenRefreshException(refreshTokenRequest,
            "Không tồn tại refresh token này"));

    boolean isExpired = verifyExpiration(tokenDTO);
    if (!isExpired) {
      return BaseResponse.builder()
          .responseInfo(ResponseInfo.builder()
              .code(CodeConstant.INVALID_REQUEST_CODE)
              .status(ResponseType.INVALID_REQUEST.name())
              .message("Refresh token đã hết hạn vui lòng đăng nhập lại")
              .build())
          .content(null)
          .build();
    }

    return BaseResponse.builder()
        .responseInfo(ResponseInfo.builder()
            .code(CodeConstant.SUCCESS_CODE)
            .status(ResponseType.SUCCESS.name())
            .message("Thành công")
            .build())
        .content(AuthResponse.builder()
            .accessToken(jwtUtils.generateToken(tokenDTO.getUsername()))
            .refreshToken(tokenDTO.getRefreshToken())
            .tokenType("Bearer")
            .build())
        .build();
  }

  @Override
  @Transactional
  public int deletedByToken(String token) {
    log.info("DELETE TOKEN: {}", token);
    return refreshTokenRepository.deleteByToken(token);
  }
}
