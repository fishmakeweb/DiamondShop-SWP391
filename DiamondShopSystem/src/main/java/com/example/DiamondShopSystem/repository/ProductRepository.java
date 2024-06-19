package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.dto.JewelryDTO;
import com.example.DiamondShopSystem.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByProductIdAsc();
    Optional<Product> findByJewelryJewelryId(Long jewelryId);
    Page<Product> findAllByJewelryIsNotNullOrderByJewelryDateDesc(Pageable pageable);

}
