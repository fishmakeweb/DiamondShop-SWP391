package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);

    Optional<Customer> findByEmail(String email);

    @Query("SELECT u FROM Customer u WHERE u.username = ?1")
    public Customer getUserByUsername(String username);

    @Query("SELECT c FROM Customer c WHERE c.email = ?1")
    public Customer findByAssignEmail(String email);

    public Customer findByResetPasswordToken(String token);
}
