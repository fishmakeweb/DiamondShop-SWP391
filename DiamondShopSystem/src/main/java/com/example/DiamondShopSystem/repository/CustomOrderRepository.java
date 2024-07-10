package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.dto.CustomOrderDTO;
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

    @Query("SELECT c FROM CustomOrder c WHERE c.orderStatus.statusId = ?1")
    List<CustomOrder> findCustomOrderByStatus(Long customOrderId);

    @Query("SELECT new com.example.DiamondShopSystem.dto.CustomOrderDTO(" +
            "co.customOrderId, co.username, co.startDate, co.finishDate, co.orderStatus.statusDescription, co.prepaid, co.fullpaid) " +
            "FROM CustomOrder co " +
            "WHERE co.customOrderId IN (SELECT DISTINCT coa.customOrder.customOrderId FROM CustomOrderChatMessage coa)")
    List<CustomOrderDTO> findCustomOrdersWithChatMessages();
}
