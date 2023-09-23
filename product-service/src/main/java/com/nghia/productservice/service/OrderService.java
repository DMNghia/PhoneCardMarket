package com.nghia.productservice.service;

import com.nghia.productservice.dto.request.OrderRequest;
import com.nghia.productservice.dto.response.BaseResponse;
import org.springframework.security.core.Authentication;

public interface OrderService {

  BaseResponse<?> purchase(OrderRequest request, Authentication authentication);
}
