package com.nghia.productservice.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {

  @NotNull
  private Integer productId;
  @NotNull
  private Integer quantity;

}
