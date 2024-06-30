package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.OrderDTO;
import com.example.DiamondShopSystem.model.Order;
import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.repository.OrderDetailRepository;
import com.example.DiamondShopSystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    //Place holder
    public Order saveOrderOnCheckOut(Order order){
        //Not completed
        return new Order();
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private JWTUtils jwtUtils;

//    public List<OrderDetail> getCart(String token) {
//        String username = jwtUtils.extractUsername(token);
//        Long activeOrder = orderRepository.findActiveOrderByUsername(username);
//        return orderDetailRepository.findByOrderId(activeOrder);
//        // Return an empty list if there is no active order
//    }

    public OrderDTO getCart(String token) {
//        OrderDTO dto = new OrderDTO();
        String username = jwtUtils.extractUsername(token);
        Optional<Order> activeOrder = orderRepository.findActiveOrderByUsername(username);
        return new OrderDTO(orderDetailRepository.findByOrderId(activeOrder.get().getOrderId()),activeOrder.get().getTotalPrice());
        // Return an empty list if there is no active order
    }

    public List<Order> getOrders(String token) {
        String username = jwtUtils.extractUsername(token);
        return orderRepository.findFinshiedOrderByUsername(username);
    }

}
