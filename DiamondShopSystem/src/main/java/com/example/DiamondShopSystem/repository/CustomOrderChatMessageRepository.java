package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.CustomOrder;
import com.example.DiamondShopSystem.model.CustomOrderChatMessage;
import com.example.DiamondShopSystem.model.OrderChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  CustomOrderChatMessageRepository extends JpaRepository<CustomOrderChatMessage, Long> {

    List<CustomOrderChatMessage> findByCustomOrderCustomOrderId(Long customOrderId);


}
