package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}
