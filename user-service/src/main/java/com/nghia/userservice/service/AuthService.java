package com.nghia.userservice.service;

import com.nghia.userservice.dto.request.LoginRequest;
import com.nghia.userservice.dto.request.RefreshTokenRequest;
import com.nghia.userservice.dto.request.SignUpRequest;
import com.nghia.userservice.dto.response.BaseResponse;

public interface AuthService {

  BaseResponse<?> login(LoginRequest request);
  BaseResponse<?> signup(SignUpRequest request);
  BaseResponse<?> refreshToken(RefreshTokenRequest request);
  BaseResponse<?> activeAccount(String username, String token);
}
