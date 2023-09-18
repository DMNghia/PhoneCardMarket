package com.nghia.productservice.repository;

import com.nghia.productservice.entity.Product;
import com.nghia.productservice.entity.Storage;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends PagingAndSortingRepository<Storage, Long> {

  @Query(value = "select Storage from Storage where product = :product "
      + "and isUsed = false "
      + "order by createdAt desc "
      + "limit 1")
  Optional<Storage> findLastAvailableStorage(Product product);

  @Query(value = "select Storage from Storage "
      + "left join Product on Product = Storage.product")
  List<Storage> findAllAvailable(Pageable pageable, Sort sort);
}
