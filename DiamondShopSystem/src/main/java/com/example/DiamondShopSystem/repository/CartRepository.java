package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
