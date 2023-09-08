package com.nghia.userservice.repository;

import com.nghia.userservice.entity.Role;
import com.nghia.userservice.entity.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByName(RoleName name);
}
