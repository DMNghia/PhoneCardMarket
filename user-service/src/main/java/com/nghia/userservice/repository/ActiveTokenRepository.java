package com.nghia.userservice.repository;

import com.nghia.userservice.entity.ActiveToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActiveTokenRepository extends JpaRepository<ActiveToken, Long> {
  ActiveToken findByToken(String token);
}
