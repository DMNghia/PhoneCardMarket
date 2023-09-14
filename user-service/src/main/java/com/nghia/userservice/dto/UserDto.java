package com.nghia.userservice.dto;

import com.nghia.userservice.entity.Role;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

  private Integer id;
  private String username;
  private String email;
  private boolean isEnable;
  private boolean isLocked;
  private Role role;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime lockedAt;
}
