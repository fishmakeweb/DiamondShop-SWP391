package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Jewelry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JewelryRepository extends JpaRepository<Jewelry, Long> {
    boolean existsByName(String name);
}
