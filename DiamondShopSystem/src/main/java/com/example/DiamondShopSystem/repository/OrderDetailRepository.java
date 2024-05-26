package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.OrderDetail;
import com.example.DiamondShopSystem.model.OrderDetail.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
