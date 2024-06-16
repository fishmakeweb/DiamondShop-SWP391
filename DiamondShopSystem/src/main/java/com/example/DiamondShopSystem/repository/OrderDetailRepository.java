package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.OrderDetail;
import com.example.DiamondShopSystem.model.OrderDetail.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
    List<OrderDetail> findByOrderOrderId(Long orderId);

}
