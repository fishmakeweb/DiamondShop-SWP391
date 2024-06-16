package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.OrderProcess;
import com.example.DiamondShopSystem.repository.OrderProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderProcessService {

    @Autowired
    private OrderProcessRepository orderProcessRepository;

    public List<OrderProcess> findAllOrderProcesses() {
        return orderProcessRepository.findAll();
    }

    public OrderProcess findOrderProcessById(Long id) {
        Optional<OrderProcess> orderProcess = orderProcessRepository.findById(id);
        return orderProcess.orElse(null);
    }

    public OrderProcess saveOrderProcess(OrderProcess orderProcess) {
        return orderProcessRepository.save(orderProcess);
    }

    public OrderProcess updateOrderProcess(Long id, OrderProcess newOrderProcess) {
        return orderProcessRepository.findById(id)
                .map(existingOrderProcess -> {
                    existingOrderProcess.setOrder(newOrderProcess.getOrder());
                    existingOrderProcess.setOrderProcessDescription(newOrderProcess.getOrderProcessDescription());
                    return orderProcessRepository.save(existingOrderProcess);
                }).orElseGet(() -> {
                    newOrderProcess.setOrderprocessId(id);
                    return orderProcessRepository.save(newOrderProcess);
                });
    }

    public void deleteOrderProcess(Long id) {
        orderProcessRepository.deleteById(id);
    }
}
