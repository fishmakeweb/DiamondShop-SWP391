package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.dto.OrderChatDTO;
import com.example.DiamondShopSystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    Order findByCustomerUserIdAndOrderStatusStatusId(Long userId, Long statusId);

    @Query("SELECT o FROM Order o WHERE o.username = ?1 AND o.orderStatus.statusId = 1")
    Optional<Order> findActiveOrderByUsername(String username);

//    @Query("SELECT o FROM Order o WHERE o.username = ?1 AND o.orderStatus.statusId = 4")
//    List<Order> findFinshiedOrderByUsername(String username);

    @Query("SELECT o FROM Order o WHERE o.username = ?1 AND o.orderStatus.statusId = 4")
    List<Order> findFinshiedOrderByUsername(String username);

    @Query("SELECT o FROM Order o WHERE o.orderStatus.statusId = ?1")
    List<Order> findOrderByStatus(Long statusId);

    @Query("SELECT new com.example.DiamondShopSystem.dto.OrderChatDTO(" +
            "o.orderId, o.username, o.orderStatus.statusDescription, o.orderDate, o.totalPrice) " +
            "FROM Order o " +
            "WHERE o.orderId IN (SELECT DISTINCT oc.order.orderId FROM OrderChatMessage oc)")
    List<OrderChatDTO> findOrdersWithChatMessages();
}
