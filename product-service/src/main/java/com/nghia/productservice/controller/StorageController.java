package com.nghia.productservice.controller;

import com.nghia.productservice.entity.Product;
import com.nghia.productservice.repository.ProductRepository;
import com.nghia.productservice.repository.StorageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/storage")
public class StorageController {
  private final ProductRepository productRepository;
  private final StorageRepository storageRepository;

  public StorageController(ProductRepository productRepository, StorageRepository storageRepository) {
    this.productRepository = productRepository;
    this.storageRepository = storageRepository;
  }

  @GetMapping("/getAvailable")
  public ResponseEntity<?> getAvailable(@RequestParam Integer productId) {
    Product product = productRepository.findById(productId).orElseThrow();
    return ResponseEntity.ok(
        storageRepository.findTopByIsUsedAndProductOrderByCreatedAtDesc(false, product));
  }
  @GetMapping("/getAllNotUse")
  public ResponseEntity<?> getAllNotUse(
      @RequestParam Integer providerId,
      @RequestParam Double price,
      @RequestParam Integer pageNo,
      @RequestParam Integer pageSize
      ) {
    Pageable pageable = PageRequest.of(pageNo, pageSize);
    return ResponseEntity.ok(
        storageRepository.findAllNotUsed(providerId, price, pageable));
  }

}
