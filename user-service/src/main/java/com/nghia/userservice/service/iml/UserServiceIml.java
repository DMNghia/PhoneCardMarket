package com.nghia.userservice.service.iml;

import com.nghia.userservice.dto.MessageDTO;
import com.nghia.userservice.dto.UserDto;
import com.nghia.userservice.dto.request.SignUpRequest;
import com.nghia.userservice.entity.ActiveToken;
import com.nghia.userservice.entity.Role;
import com.nghia.userservice.entity.RoleName;
import com.nghia.userservice.entity.User;
import com.nghia.userservice.exception.MessageException;
import com.nghia.userservice.repository.RoleRepository;
import com.nghia.userservice.repository.UserRepository;
import com.nghia.userservice.service.ActiveTokenService;
import com.nghia.userservice.service.MessageService;
import com.nghia.userservice.service.UserService;
import com.nghia.userservice.service.kafka.producer.ProducerService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Slf4j
public class UserServiceIml implements UserService {

  private final PasswordEncoder passwordEncoder;
  private final RoleRepository roleRepository;
  private final ActiveTokenService activeTokenService;
  private final UserRepository userRepository;
  private final ProducerService producerService;
  private final MessageService messageService;
  private final ModelMapper modelMapper;

  public UserServiceIml(PasswordEncoder passwordEncoder, RoleRepository roleRepository,
      ActiveTokenService activeTokenService, UserRepository userRepository,
      ProducerService producerService, MessageService messageService, ModelMapper modelMapper) {
    this.passwordEncoder = passwordEncoder;
    this.roleRepository = roleRepository;
    this.activeTokenService = activeTokenService;
    this.userRepository = userRepository;
    this.producerService = producerService;
    this.messageService = messageService;
    this.modelMapper = modelMapper;
  }

  @Override
  public Optional<User> register(SignUpRequest request) {
    Role role = roleRepository.findByName(RoleName.USER).orElse(null);
    User newUser = User.builder()
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .createdAt(LocalDateTime.now())
        .isEnable(false)
        .isLocked(false)
        .role(role)
        .build();
    User user = userRepository.save(newUser);

    ActiveToken activeToken = activeTokenService.newActiveToken(user);
    MessageDTO messageDTO = messageService.messageActive(user, activeToken.getToken())
        .orElseThrow(() -> new MessageException("Error null"));
    producerService.send(messageDTO);

    return Optional.of(newUser);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    User user = userRepository.findByUsername(username).orElse(null);
    if (ObjectUtils.isEmpty(user)) {
      log.info("REQUEST FIND USER BY USERNAME - {} -> FAIL USER NOT EXIST", username);
      return Optional.empty();
    }
    return Optional.of(user);
  }

  @Override
  public Optional<User> findById(int id) {
    return userRepository.findById(id);
  }

  @Override
  public UserDto updateUser(User user) {
    user.setUpdatedAt(LocalDateTime.now());
    return modelMapper.map(userRepository.save(user), UserDto.class);
  }
}
