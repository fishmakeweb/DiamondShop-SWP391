package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.CustomJewelry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomJewelryRepository extends JpaRepository<CustomJewelry, Long> {
}
