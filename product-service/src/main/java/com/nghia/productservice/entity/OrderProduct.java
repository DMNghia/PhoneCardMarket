package com.nghia.productservice.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_product")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "order_id")
  private Order order;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id")
  private Product product;
}
