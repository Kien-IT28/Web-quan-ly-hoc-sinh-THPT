package com.dev.quanlyhocsinh.repository;


import com.dev.quanlyhocsinh.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(Long id);
    Optional<Role> findByName(String name);
}
