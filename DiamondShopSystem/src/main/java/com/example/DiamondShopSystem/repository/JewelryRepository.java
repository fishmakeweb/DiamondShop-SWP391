package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.model.Jewelry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JewelryRepository extends JpaRepository<Jewelry, Long> {
}
