package com.nghia.cashservice.repository;

import com.nghia.cashservice.entity.Wallet;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
  Optional<Wallet> findByUsername(String username);
}
