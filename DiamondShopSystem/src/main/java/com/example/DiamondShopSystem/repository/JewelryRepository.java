package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.dto.JewelryDTO;
import com.example.DiamondShopSystem.model.Jewelry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.List;

public interface JewelryRepository extends JpaRepository<Jewelry, Long> {
    boolean existsByName(String name);
    Jewelry findByJewelryId(Long jewelryId);

    @Query("SELECT new com.example.DiamondShopSystem.dto.JewelryDTO(j.jewelryId, j.name, j.price, j.img) " +
            "FROM Jewelry j " +
            "WHERE j.isSold = false " +
            "ORDER BY j.jewelryId")
    Page<JewelryDTO> findJewelryByPage(Pageable pageable);

    Page<Jewelry> findByIsSoldFalse(Pageable pageable);

    Page<Jewelry> findByIsSoldFalseAndMaterialMaterialIdInAndCategoryCategoryIdInAndShapeShapeIdInAndSizeSizeIdInAndPriceBetween(
            List<Long> materialIds, List<Long> categoryIds, List<Long> shapeIds, List<Long> sizeIds, Float minPrice, Float maxPrice, Pageable pageable);

    @Query("SELECT new com.example.DiamondShopSystem.dto.JewelryDTO(j.jewelryId, j.name, j.price, j.img) " +
            "FROM Jewelry j " +
            "WHERE j.isSold = false " +
            "AND (coalesce(:materialIds, null) IS NULL OR j.material.materialId IN :materialIds) " +
            "AND (coalesce(:categoryIds, null) IS NULL OR j.category.categoryId IN :categoryIds) " +
            "AND (coalesce(:shapeIds, null) IS NULL OR j.shape.shapeId IN :shapeIds) " +
            "AND (coalesce(:sizeIds, null) IS NULL OR j.size.sizeId IN :sizeIds) " +
            "AND (coalesce(:minPrice, null) IS NULL OR j.price >= :minPrice) " +
            "AND (coalesce(:maxPrice, null) IS NULL OR j.price <= :maxPrice)" +
            "ORDER BY j.jewelryId")
    Page<JewelryDTO> findByFilters(@Param("materialIds") List<Long> materialIds,
                                   @Param("categoryIds") List<Long> categoryIds,
                                   @Param("shapeIds") List<Long> shapeIds,
                                   @Param("sizeIds") List<Long> sizeIds,
                                   @Param("minPrice") Float minPrice,
                                   @Param("maxPrice") Float maxPrice,
                                   Pageable pageable);


    @Query("SELECT new com.example.DiamondShopSystem.dto.JewelryDTO(j.jewelryId, j.name, j.price, j.img) " +
            "FROM Jewelry j " +
            "WHERE j.isSold = false " +
            "ORDER BY j.date DESC")  // Order by date in descending order to get the newest items first
    Page<JewelryDTO> findNewReleaseJewelry(Pageable pageable);
    @Query("SELECT j FROM Jewelry j WHERE j.category.categoryId = ?1")
    List<Jewelry> findJewelryByCategory(Long categoryId);

    @Query("SELECT new com.example.DiamondShopSystem.dto.JewelryDTO(j.jewelryId, j.name, j.price, j.img) " +
            "FROM Jewelry j " +
            "WHERE (:materialIds IS NULL OR j.material.materialId IN :materialIds) " +
            "AND (:categoryIds IS NULL OR j.category.categoryId IN :categoryIds) " +
            "AND (:shapeIds IS NULL OR j.shape.shapeId IN :shapeIds) " +
            "AND (:sizeIds IS NULL OR j.size.sizeId IN :sizeIds) " +
            "AND (:minPrice IS NULL OR j.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR j.price <= :maxPrice) " +
            "AND j.isSold = false " +
            "ORDER BY j.jewelryId")
    Page<JewelryDTO> findJewelryByFilters(List<Long> materialIds,
                                          List<Long> categoryIds,
                                          List<Long> shapeIds,
                                          List<Long> sizeIds,
                                          Float minPrice,
                                          Float maxPrice,
                                          Pageable pageable);


}
