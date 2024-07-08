package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Order;
import com.example.DiamondShopSystem.model.OrderDetail;
import com.example.DiamondShopSystem.model.Product;
import com.example.DiamondShopSystem.repository.OrderDetailRepository;
import com.example.DiamondShopSystem.repository.OrderRepository;
import com.example.DiamondShopSystem.repository.ProductRepository;
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
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    private JWTUtils jwtUtils;

    @Transactional
    public void addToCart(String token, Long productId) {
        String username = jwtUtils.extractUsername(token);

        // Find the active order for the user or create a new one
        Optional<Order> orderObj = orderRepository.findActiveOrderByUsername(username);

        // Check if the product already exists in the order details
        Optional<OrderDetail> existingDetail = orderDetailRepository
                .findByOrderIdAndProductProductId(orderObj.get().getOrderId(), productId);

        if (existingDetail.isPresent()) {
            // Product already in cart, do nothing
            return;
        }

        // Create new OrderDetail since the product is not in the cart
        OrderDetail newDetail = new OrderDetail();
        Product product = productRepository.findById(productId).get();
        newDetail.setOrderId(orderObj.get().getOrderId());

        newDetail.setProduct(product);

        newDetail.setQuantity(1);  // Quantity is set to 1
        orderObj.get().setTotalPrice(orderObj.get().getTotalPrice()+product.getJewelry().getPrice());
        // Save the new order detail
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
                orderObj.setTotalPrice(orderObj.getTotalPrice()-orderDetail.getQuantity()*orderDetail.getProduct().getJewelry().getPrice());
                orderRepository.save(orderObj);
                orderDetailRepository.delete(orderDetail);
                return null; // Return null or handle as needed after doeletin
            } else {
                // Update quantity if it is not zero
                orderObj.setTotalPrice(orderObj.getTotalPrice()+(quantity-orderDetail.getQuantity())*orderDetail.getProduct().getJewelry().getPrice());
                orderRepository.save(orderObj);
                orderDetail.setQuantity(quantity);
                return orderDetailRepository.save(orderDetail);
            }
        }
        return null; // or throw an exception as per your error handling strategy
    }

}
