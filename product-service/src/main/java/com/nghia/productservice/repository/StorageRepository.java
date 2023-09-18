package com.nghia.productservice.repository;

import com.nghia.productservice.entity.Product;
import com.nghia.productservice.entity.Storage;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

  Optional<Storage> findTopByIsUsedAndProductOrderByCreatedAtDesc(boolean isUsed, Product product);

  @Query(value = "select s from Storage s "
      + "where s.product.provider.id = :providerId "
      + "and s.product.price = :price "
      + "and s.isUsed = false")
  List<Storage> findAllNotUsed(
      @Param("providerId") Integer providerId,
      @Param("price") Double price,
      Pageable pageable
  );

  @Query(value = "select s from Storage s "
      + "where s.product.provider.id = :providerId "
      + "and s.product.price = :price ")
  List<Storage> findAll(
      @Param("providerId") Integer providerId,
      @Param("price") Double price,
      Pageable pageable
  );
}
