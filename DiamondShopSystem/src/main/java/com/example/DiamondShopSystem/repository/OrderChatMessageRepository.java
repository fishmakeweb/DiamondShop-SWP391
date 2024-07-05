package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.OrderChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderChatMessageRepository extends JpaRepository<OrderChatMessage, Long> {
    List<OrderChatMessage> findByOrderOrderId(Long orderId);
}
