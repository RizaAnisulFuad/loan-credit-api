package com.enigma.load_credit_api.repository;

import com.enigma.load_credit_api.constant.UserRole;
import com.enigma.load_credit_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findRoleBy(UserRole role);
}
