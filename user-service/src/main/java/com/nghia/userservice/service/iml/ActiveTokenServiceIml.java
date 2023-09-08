package com.nghia.userservice.service.iml;

import com.nghia.userservice.entity.ActiveToken;
import com.nghia.userservice.entity.User;
import com.nghia.userservice.repository.ActiveTokenRepository;
import com.nghia.userservice.service.ActiveTokenService;
import com.nghia.userservice.service.kafka.producer.ProducerService;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ActiveTokenServiceIml implements ActiveTokenService {

  private final ActiveTokenRepository activeTokenRepository;
  private final ProducerService producerService;

  public ActiveTokenServiceIml(ActiveTokenRepository activeTokenRepository,
      ProducerService producerService) {
    this.activeTokenRepository = activeTokenRepository;
    this.producerService = producerService;
  }

  @Override
  public ActiveToken newActiveToken(User user) {
    ActiveToken activeToken = ActiveToken.builder()
        .token(UUID.randomUUID().toString())
        .createdAt(LocalDateTime.now())
        .expiredAt(LocalDateTime.now().plusHours(24L))
        .user(user)
        .isUsed(false)
        .build();

    return activeTokenRepository.save(activeToken);
  }

  @Override
  public Optional<ActiveToken> findByToken(String token) {
    Optional<ActiveToken> activeTokenOptional =
        Optional.ofNullable(activeTokenRepository.findByToken(token));

    if (activeTokenOptional.isEmpty()) {
      log.info("REQUEST TOKEN - {} -> FAIL TOKEN NOT FOUND", token);
      return Optional.empty();
    }
    return activeTokenOptional;
  }
}
