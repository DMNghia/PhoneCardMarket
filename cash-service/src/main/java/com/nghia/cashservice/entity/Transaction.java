package com.nghia.cashservice.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "transaction")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer userId;
  private String username;
  private Long amount;
  private String description;
  @Enumerated(EnumType.STRING)
  private StatusTransaction status;
  @Enumerated(EnumType.STRING)
  private TransactionType type;
  @CreatedDate
  private LocalDateTime createdAt;
}
