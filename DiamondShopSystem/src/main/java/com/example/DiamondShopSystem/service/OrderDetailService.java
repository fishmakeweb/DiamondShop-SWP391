package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Jewelry;
import com.example.DiamondShopSystem.model.Order;
import com.example.DiamondShopSystem.model.OrderDetail;
import com.example.DiamondShopSystem.repository.JewelryRepository;
import com.example.DiamondShopSystem.repository.OrderDetailRepository;
import com.example.DiamondShopSystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.delete(orderDetail);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }



    @Autowired
    JewelryRepository jewelryRepository;

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    private JWTUtils jwtUtils;

    @Transactional
    public void addToCart(String token, Long jewelryId) {
        String username = jwtUtils.extractUsername(token);

        // Find the active order for the user or create a new one
        Optional<Order> orderObj = orderRepository.findActiveOrderByUsername(username);

        // Check if the product already exists in the order details
        Optional<OrderDetail> existingDetail = orderDetailRepository
                .findByOrderIdAndJewelryJewelryId(orderObj.get().getOrderId(), jewelryId);

        if (existingDetail.isPresent()) {
            // Product already in cart, do nothing
            return;
        }

        // Create new OrderDetail since the product is not in the cart
        OrderDetail newDetail = new OrderDetail();
        Jewelry jewelry = jewelryRepository.findByJewelryId(jewelryId);
        newDetail.setOrderId(orderObj.get().getOrderId());

        newDetail.setJewelry(jewelry);

        newDetail.setQuantity(1);  // Quantity is set to 1
        orderObj.get().setTotalPrice(orderObj.get().getTotalPrice()+jewelry.getPrice());
        orderDetailRepository.save(newDetail);
    }
    @Transactional
    public OrderDetail updateQuantity(Long orderDetailId, int quantity) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(orderDetailId);
        if (optionalOrderDetail.isPresent()) {
            OrderDetail orderDetail = optionalOrderDetail.get();
            Order orderObj = orderRepository.findById(optionalOrderDetail.get().getOrderId()).get();
            if (quantity == 0) {
                // If quantity is 0, delete the OrderDetail from the database
                orderObj.setTotalPrice(orderObj.getTotalPrice()-orderDetail.getQuantity()*orderDetail.getJewelry().getPrice());
                orderRepository.save(orderObj);
                orderDetailRepository.delete(orderDetail);
                return null; // Return null or handle as needed after doeletin
            } else {
                // Update quantity if it is not zero
                orderObj.setTotalPrice(orderObj.getTotalPrice()+(quantity-orderDetail.getQuantity())*orderDetail.getJewelry().getPrice());
                orderRepository.save(orderObj);
                orderDetail.setQuantity(quantity);
                return orderDetailRepository.save(orderDetail);
            }
        }
        return null; // or throw an exception as per your error handling strategy
    }

    public List<OrderDetail> getOrderDetailByJewelryId(Long id) {
        return orderDetailRepository.findByJewelryJewelryId(id);
    }

}
