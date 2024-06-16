package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.OrderProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProcessRepository extends JpaRepository<OrderProcess, Long> {
}
