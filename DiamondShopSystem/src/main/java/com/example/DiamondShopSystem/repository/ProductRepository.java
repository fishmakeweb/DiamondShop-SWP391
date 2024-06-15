package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.dto.JewelryDTO;
import com.example.DiamondShopSystem.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT new com.example.DiamondShopSystem.dto.JewelryDTO(p.productId, j.jewelryId, j.name, j.price, j.img) " +
            "FROM Product p INNER JOIN p.jewelry j")
//    List<JewelryDTO> findAllJewelryDTOs();
    Page<JewelryDTO> findAllJewelryDTOs(Pageable pageable);

}
