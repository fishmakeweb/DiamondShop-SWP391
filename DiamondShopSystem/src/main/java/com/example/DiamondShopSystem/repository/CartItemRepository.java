package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.CartItem;
import com.example.DiamondShopSystem.model.CartItem.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
}
