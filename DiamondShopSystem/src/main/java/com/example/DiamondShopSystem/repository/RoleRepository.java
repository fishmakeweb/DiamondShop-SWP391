package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String roleName);
}
