package com.nghia.userservice;

import com.nghia.userservice.dto.request.SignUpRequest;
import com.nghia.userservice.entity.Role;
import com.nghia.userservice.entity.RoleName;
import com.nghia.userservice.entity.User;
import com.nghia.userservice.repository.RoleRepository;
import com.nghia.userservice.service.AuthService;
import com.nghia.userservice.service.UserService;
import com.nghia.userservice.service.grpc.CashServiceGrpcIml;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserServiceApplication {

  private final UserService userService;
  private final RoleRepository roleRepository;
  private final CashServiceGrpcIml cashServiceGrpcIml;
  private final AuthService authService;

  public UserServiceApplication(UserService userService, RoleRepository roleRepository,
      CashServiceGrpcIml cashServiceGrpcIml, AuthService authService) {
    this.userService = userService;
    this.roleRepository = roleRepository;
    this.cashServiceGrpcIml = cashServiceGrpcIml;
    this.authService = authService;
  }

  public static void main(String[] args) {
    SpringApplication.run(UserServiceApplication.class, args);
  }

//  @PostConstruct
//  public void createSomeRecord() {
//    if (roleRepository.findByName(RoleName.USER).isEmpty()) {
//      roleRepository.save(Role.builder()
//          .name(RoleName.USER)
//          .build());
//    }
//    if (roleRepository.findByName(RoleName.ADMIN).isEmpty()) {
//      roleRepository.save(Role.builder()
//          .name(RoleName.ADMIN)
//          .build());
//    }
//    if (roleRepository.findByName(RoleName.STAFF).isEmpty()) {
//      roleRepository.save(Role.builder()
//          .name(RoleName.STAFF)
//          .build());
//    }
//    if (userService.findById(1).isEmpty()) {
//      authService.signup(SignUpRequest.builder()
//          .email("dmnghia1511@gmail.com")
//          .username("sys_admin")
//          .password("Admin123")
//          .build());
//      Optional<User> userOptional = userService.findById(1);
//      userOptional.ifPresent(
//          user -> user.setRole(roleRepository.findByName(RoleName.ADMIN).orElse(null)));
//    }
//  }

  @Bean
  NewTopic mail() {
    // topic name , partition numbers, replication number
    return new NewTopic("mail", 2, (short) 3);
  }

  @Bean
  NewTopic notification() {
    return new NewTopic("notification", 2, (short) 3);
  }

}
