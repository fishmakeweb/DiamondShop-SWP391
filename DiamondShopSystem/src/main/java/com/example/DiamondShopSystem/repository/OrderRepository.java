package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    Order findByCustomerUserIdAndOrderStatusStatusId(Long userId, Long statusId);

    @Query("SELECT o FROM Order o WHERE o.username = ?1 AND o.orderStatus.statusId = 1")
    Optional<Order> findActiveOrderByUsername(String username);

    @Query("SELECT o FROM Order o WHERE o.username = ?1 AND o.orderStatus.statusId = 4")
    List<Order> findFinshiedOrderByUsername(String username);
}
