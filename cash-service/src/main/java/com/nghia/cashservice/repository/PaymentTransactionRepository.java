package com.nghia.cashservice.repository;

import com.nghia.cashservice.entity.PaymentTransaction;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {

  PaymentTransaction findByVnpTxnRefAndVnpTmnCode(String vnpay_TxnRef, String vnpay_TmnCode);
}
