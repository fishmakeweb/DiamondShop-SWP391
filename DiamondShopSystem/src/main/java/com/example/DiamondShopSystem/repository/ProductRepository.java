package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
