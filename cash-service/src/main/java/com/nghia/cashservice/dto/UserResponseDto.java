package com.nghia.cashservice.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

  private Integer id;
  private String username;
  private String email;
  private boolean isEnable;
  private boolean isLocked;
  private LocalDateTime createdAt;
  private String roleName;
}
