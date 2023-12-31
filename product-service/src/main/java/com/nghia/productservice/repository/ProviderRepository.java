package com.nghia.productservice.repository;

import com.nghia.productservice.entity.Provider;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {
  Optional<Provider> findById(Integer id);
}
