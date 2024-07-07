package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.dto.JewelryDTO;
import com.example.DiamondShopSystem.dto.NewReleaseDTO;
import com.example.DiamondShopSystem.model.Jewelry;
import com.example.DiamondShopSystem.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByProductIdAsc();
    Optional<Product> findByJewelryJewelryId(Long jewelryId);
    Page<Product> findAllByJewelryIsNotNullOrderByJewelryDateDesc(Pageable pageable);
    @Query("SELECT new com.example.DiamondShopSystem.dto.JewelryDTO(p.productId, j.name, j.price, j.img) " +
            "FROM Product p INNER JOIN p.jewelry j " +
            "WHERE p.jewelry.jewelryId = j.jewelryId")
    List<JewelryDTO> findJewelryDTOs();

    @Query("SELECT new com.example.DiamondShopSystem.dto.NewReleaseDTO(p.productId, j.name, j.price, j.img, j.date) " +
            "FROM Product p INNER JOIN p.jewelry j " +
            "WHERE p.jewelry.jewelryId = j.jewelryId " +
            "ORDER BY j.date DESC")
    List<NewReleaseDTO> findNewReleaseDTOs();

    @Query("SELECT new com.example.DiamondShopSystem.dto.JewelryDTO(p.productId, j.name, j.price, j.img) " +
            "FROM Product p JOIN p.jewelry j " +
            "ORDER BY j.jewelryId")
    Page<JewelryDTO> findJewelryByPage(Pageable pageable);

    @Query("SELECT new com.example.DiamondShopSystem.dto.NewReleaseDTO(p.productId, j.name, j.price, j.img, j.date) " +
            "FROM Product p JOIN p.jewelry j " +
            "ORDER BY j.date DESC")
    Page<NewReleaseDTO> findNewReleaseByPage(Pageable pageable);



}
