package com.nghia.productservice.service.iml;

import com.nghia.productservice.dto.request.OrderRequest;
import com.nghia.productservice.dto.response.BaseResponse;
import com.nghia.productservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceIml implements OrderService {

  @Override
  public BaseResponse<?> purchase(OrderRequest request, Authentication authentication) {

    return null;
  }
}
