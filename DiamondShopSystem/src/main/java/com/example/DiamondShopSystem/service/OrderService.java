package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.dto.OrderDTO;
import com.example.DiamondShopSystem.model.Customer;
import com.example.DiamondShopSystem.model.Order;
import com.example.DiamondShopSystem.model.OrderDetail;
import com.example.DiamondShopSystem.repository.CustomerRepository;
import com.example.DiamondShopSystem.repository.OrderDetailRepository;
import com.example.DiamondShopSystem.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<OrderDTO> getOrders(String token) {
        // Extract username from the token
        String username = jwtUtils.extractUsername(token);

        // Retrieve all finished orders for the username
        List<Order> orders = orderRepository.findFinshiedOrderByUsername(username);

        // Use Java streams and map to convert orders to OrderDTOs
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> {
                    // Find all order details for the current order
                    List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.getOrderId());

                    // Retrieve the total price of the current order
                    float totalPrice = order.getTotalPrice();

                    // Return an OrderDTO object for the current order
                    return new OrderDTO(orderDetails, totalPrice);
                })
                .collect(Collectors.toList());

        // Return the list of OrderDTO objects
        return orderDTOs;
    }

}
