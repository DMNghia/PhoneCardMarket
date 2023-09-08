package com.nghia.userservice.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
  @Valid

  @NotNull(message = "Tên đăng nhập không được để trống")
  @NotBlank(message = "Tên đăng nhập không được để trống")
  @Size(min = 3, max = 50, message = "Tên đăng nhập có độ dài từ 3 đến 50 ký tự")
  private String username;

  @NotBlank(message = "Email không được để trống")
  @NotNull(message = "Email không được để trống")
  @Email(message = "Email không đúng định dạng")
  private String email;

  @NotNull(message = "Mật khẩu không được để trống")
  @NotBlank(message = "Mật khẩu không được để trống")
  @Size(min = 5, max = 30, message = "Mật khẩu bao gồm từ 5 đến 30 ký tự")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?!.*\\s).+$",
      message = "Mật khẩu phải có ít nhất một chữ hoa, một chữ thường và một chữ số")
  private String password;
}
