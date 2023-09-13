package com.nghia.cashservice.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "payment_transaction")
@Data
public class PaymentTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Double amount;
  private String vnpaySecureHash;
  private Integer userId;
  private String username;

  @Enumerated(EnumType.STRING)
  private StatusTransaction status;

  @CreatedDate
  private LocalDateTime createdAt;
}
