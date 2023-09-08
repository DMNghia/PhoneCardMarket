package com.nghia.userservice.service.iml;

import com.nghia.userservice.dto.RefreshTokenDTO;
import com.nghia.userservice.dto.response.AuthResponse;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefreshTokenServiceIml implements RefreshTokenService {

  private final UserRepository userRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtUtils jwtUtils;

  public RefreshTokenServiceIml(UserRepository userRepository,
      RefreshTokenRepository refreshTokenRepository, JwtUtils jwtUtils) {
    this.userRepository = userRepository;
    this.refreshTokenRepository = refreshTokenRepository;
    this.jwtUtils = jwtUtils;
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
  public RefreshTokenDTO verifyExpiration(RefreshTokenDTO tokenDTO) {
    if (tokenDTO.getExpiredAt().isBefore(LocalDateTime.now())) {
      deletedByUser(tokenDTO.getUsername());
      throw new TokenRefreshException(tokenDTO.getRefreshToken(),
          "Mã xác thực đã hết hạn vui lòng đăng nhập lại");
    }
    return tokenDTO;
  }

  @Override
  @Transactional
  public AuthResponse refreshToken(String refreshTokenRequest) {
    RefreshTokenDTO tokenDTO = refreshTokenRepository.findByToken(refreshTokenRequest)
        .map(token -> RefreshTokenDTO.builder()
            .refreshToken(token.getToken())
            .expiredAt(token.getExpiredAt())
            .username(token.getUser().getUsername())
            .build())
        .orElseThrow(() -> new TokenRefreshException(refreshTokenRequest,
            "Không tồn tại refresh token này"));

    tokenDTO = verifyExpiration(tokenDTO);

    return AuthResponse.builder()
        .accessToken(jwtUtils.generateToken(tokenDTO.getUsername()))
        .refreshToken(tokenDTO.getRefreshToken())
        .tokenType("Bearer")
        .build();
  }

  @Override
  @Transactional
  public int deletedByUser(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    return refreshTokenRepository.deleteByUser(user
        .orElseThrow(() -> new UsernameNotFoundException("Tên đăng nhập không tìm thấy")));
  }
}
