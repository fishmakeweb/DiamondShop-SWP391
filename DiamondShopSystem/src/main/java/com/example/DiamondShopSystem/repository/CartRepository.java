package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Cart;
import com.example.DiamondShopSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer(Customer customer);
}
