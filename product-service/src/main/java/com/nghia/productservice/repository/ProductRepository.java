package com.nghia.productservice.repository;

import com.nghia.productservice.entity.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
  Optional<Product> findById(Integer id);
}
