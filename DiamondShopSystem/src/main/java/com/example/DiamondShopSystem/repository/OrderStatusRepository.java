package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
