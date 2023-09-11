package com.nghia.cashservice.dto.response;

import java.net.http.HttpResponse.ResponseInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseResponse<T> {
  private ResponseInfo responseInfo;
  private T content;
}
