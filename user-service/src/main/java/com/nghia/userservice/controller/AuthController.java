package com.nghia.userservice.controller;

import com.google.gson.Gson;
import com.nghia.userservice.dto.request.LoginRequest;
import com.nghia.userservice.dto.request.RefreshTokenRequest;
import com.nghia.userservice.dto.request.SignUpRequest;
import com.nghia.userservice.dto.response.BaseResponse;
import com.nghia.userservice.service.AuthService;
import com.nghia.userservice.service.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

  private final AuthService authService;
  private final UserService userService;
  private final Gson gson;

  public AuthController(AuthService authService, UserService userService, Gson gson) {
    this.authService = authService;
    this.userService = userService;
    this.gson = gson;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    BaseResponse<?> response = authService.login(request);
    if (!response.getResponseInfo().getCode().equals("00")) {
      return ResponseEntity.badRequest().body(response);
    }
    return ResponseEntity.ok(response);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest request) {
    BaseResponse<?> response = authService.signup(request);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/refreshToken")
  public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
    BaseResponse<?> response = authService.refreshToken(request);
    log.info("REQUEST REFRESH TOKEN:\n{}\n-> SUCCESS:\n{}", gson.toJson(request), gson.toJson(response));
    return ResponseEntity.ok(response);
  }

  @GetMapping("/activeAccount")
  public ResponseEntity<?> activeAccount(@RequestParam("username") String username,
      @RequestParam("activeToken") String token) {
    return ResponseEntity.ok(authService.activeAccount(username, token));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> responseException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(e -> {
      String field = ((FieldError) e).getField();
      String message = e.getDefaultMessage();
      errors.put(field, message);
    });

    return errors;
  }
}
