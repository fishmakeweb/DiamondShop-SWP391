package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
//    List<OrderDetail> findByOrderOrderId(Long orderId);
        List<OrderDetail> findByOrderId(Long orderId);
        Optional<OrderDetail> findByOrderIdAndJewelryJewelryId(Long orderId, Long jewelryId);

        OrderDetail findByJewelryJewelryId(Long id);
}
