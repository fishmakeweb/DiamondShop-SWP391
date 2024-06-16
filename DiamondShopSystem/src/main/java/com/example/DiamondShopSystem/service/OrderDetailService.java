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



    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    public void addProductToOrder(Long productId, Long userId) {
        // Fetch the product details
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Fetch the order for the user with status id 1
        Order order = orderRepository.findByCustomerUserIdAndOrderStatusStatusId(userId, 1L);


        // Create a new OrderDetail object
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setOrder(order);
        orderDetail.setQuantity(1); // Default quantity
        orderDetail.setUnitPrice(product.getJewelry().getPrice());

        // Save the OrderDetail object
        orderDetailRepository.save(orderDetail);
    }
}
