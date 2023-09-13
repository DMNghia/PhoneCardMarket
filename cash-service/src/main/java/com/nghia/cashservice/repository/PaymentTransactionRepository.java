package com.nghia.cashservice.repository;

import com.nghia.cashservice.entity.PaymentTransaction;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {

  Optional<PaymentTransaction> findByVnpaySecureHash(String secureHash);
}
