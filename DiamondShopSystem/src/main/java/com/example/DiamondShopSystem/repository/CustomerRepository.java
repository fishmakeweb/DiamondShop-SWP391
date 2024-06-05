package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
    void deleteByUsername(String username);
}
