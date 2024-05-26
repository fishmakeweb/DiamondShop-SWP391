package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
