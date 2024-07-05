package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.OrderStatus;
import com.example.DiamondShopSystem.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusService {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    public List<OrderStatus> findAllOrderStatuses() {
        return orderStatusRepository.findAll();
    }

    public OrderStatus findOrderStatusById(Long id) {
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(id);
        return orderStatus.orElse(null);
    }

    public OrderStatus saveOrderStatus(OrderStatus orderStatus) {
        return orderStatusRepository.save(orderStatus);
    }

    public OrderStatus updateOrderStatus(Long id, OrderStatus newOrderStatus) {
        return orderStatusRepository.findById(id)
                .map(existingOrderStatus -> {
                    existingOrderStatus.setStatusDescription(newOrderStatus.getStatusDescription());
                    return orderStatusRepository.save(existingOrderStatus);
                }).orElseThrow(() -> new RuntimeException("Status not found"));
    }

    public void deleteOrderStatus(Long id) {
        orderStatusRepository.deleteById(id);
    }
}
