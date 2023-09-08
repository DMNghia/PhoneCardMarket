package com.nghia.userservice.service;

import com.nghia.userservice.dto.RefreshTokenDTO;
import com.nghia.userservice.dto.response.AuthResponse;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenService {
  RefreshTokenDTO createRefreshToken(String username);
  RefreshTokenDTO verifyExpiration(RefreshTokenDTO tokenDTO);
  AuthResponse refreshToken(String refreshTokenRequest);
  int deletedByUser(String username);
}
