package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.OrderDetail;
import com.example.DiamondShopSystem.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.delete(orderDetail);
    }
}
