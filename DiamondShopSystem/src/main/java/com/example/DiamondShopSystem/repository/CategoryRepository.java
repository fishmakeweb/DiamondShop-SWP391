package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
