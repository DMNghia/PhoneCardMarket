package com.nghia.productservice.entity;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "storage", uniqueConstraints = {
    @UniqueConstraint(name = "unique_serial_number", columnNames = "serial_number"),
    @UniqueConstraint(name = "unique_card_code", columnNames = "card_code")
})
@SQLDelete(sql = "UPDATE storage SET is_delete = true WHERE id = ?")
@Where(clause = "is_delete = false")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Storage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "serial_number")
  private String serialNumber;

  @Column(name = "card_code")
  private String cardCode;
  private boolean isUsed;
  private boolean isDelete;

  @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id")
  private Product product;

  @CreatedDate
  private LocalDateTime createdAt;
  private LocalDateTime expiredAt;
  @LastModifiedDate
  private LocalDateTime updatedAt;
}
