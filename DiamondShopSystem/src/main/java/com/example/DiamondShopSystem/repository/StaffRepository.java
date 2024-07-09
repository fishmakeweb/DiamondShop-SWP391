package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByUsernameAndRoleRoleId(String username, Long roleId);
    Optional<Staff> findByUsername(String username);
    Optional<Staff> findByEmail(String email);

}
