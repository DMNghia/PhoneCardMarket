package com.nghia.userservice.service;

import com.nghia.userservice.entity.ActiveToken;
import com.nghia.userservice.entity.User;
import java.util.Optional;

public interface ActiveTokenService {
  ActiveToken newActiveToken(User user);
  Optional<ActiveToken> findByToken(String token);
}
