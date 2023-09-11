package com.nghia.userservice.service;

import com.nghia.userservice.dto.RefreshTokenDTO;
import com.nghia.userservice.dto.response.AuthResponse;
import com.nghia.userservice.dto.response.BaseResponse;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenService {
  RefreshTokenDTO createRefreshToken(String username);
  boolean verifyExpiration(RefreshTokenDTO tokenDTO);
  BaseResponse<?> refreshToken(String refreshTokenRequest);
  int deletedByToken(String token);
}
