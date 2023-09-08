package com.nghia.userservice.service;

import com.nghia.userservice.dto.request.SignUpRequest;
import com.nghia.userservice.entity.User;
import java.util.Optional;

public interface UserService {
  Optional<User> register(SignUpRequest request);
  Optional<User> findByUsername(String username);
  Optional<User> findById(int id);
}
