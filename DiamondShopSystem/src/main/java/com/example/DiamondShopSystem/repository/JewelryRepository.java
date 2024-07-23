package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.dto.JewelryDTO;
import com.example.DiamondShopSystem.model.Jewelry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JewelryRepository extends JpaRepository<Jewelry, Long> {
    boolean existsByName(String name);
    Jewelry findByJewelryId(Long jewelryId);

    @Query("SELECT new com.example.DiamondShopSystem.dto.JewelryDTO(j.jewelryId, j.name, j.price, j.img) " +
            "FROM Jewelry j " +
            "WHERE j.isSold = false " +
            "ORDER BY j.jewelryId")
    Page<JewelryDTO> findJewelryByPage(Pageable pageable);

    @Query("SELECT new com.example.DiamondShopSystem.dto.JewelryDTO(j.jewelryId, j.name, j.price, j.img) " +
            "FROM Jewelry j " +
            "WHERE j.isSold = false " +
            "ORDER BY j.date DESC")  // Order by date in descending order to get the newest items first
    Page<JewelryDTO> findNewReleaseJewelry(Pageable pageable);
    @Query("SELECT j FROM Jewelry j WHERE j.category.categoryId = ?1")
    List<Jewelry> findJewelryByCategory(Long categoryId);
}
