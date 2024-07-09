package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.CustomOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomOrderRepository extends JpaRepository<CustomOrder, Long> {
    List<CustomOrder> findByCustomJewelryCustomJewelryId(Long customJewelryId);
    List<CustomOrder> findByUsername(String username);

    @Query("SELECT c FROM CustomOrder c WHERE c.username = ?1 AND c.customOrderId = ?2")
    CustomOrder findByUserNameAndCustomOrderId(String username, Long customOrderId);

    CustomOrder findByCustomOrderId(Long customOrderId);
}
