package com.nghia.productservice.controller;

import com.nghia.productservice.dto.request.OrderRequest;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

  @PostMapping("/purchase")
  public ResponseEntity<?> purchase(
      @Valid @RequestBody OrderRequest request,
      Authentication authentication) {

    return null;
  }
}
