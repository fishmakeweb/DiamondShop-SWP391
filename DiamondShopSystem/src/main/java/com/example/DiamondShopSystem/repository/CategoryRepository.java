package com.example.DiamondShopSystem.repository;

import com.example.DiamondShopSystem.dto.CategoryCountDTO;
import com.example.DiamondShopSystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    @Query("SELECT new com.example.DiamondShopSystem.dto.CategoryCountDTO(c.categoryId, c.categoryName, COUNT(DISTINCT od.orderId), c.categoryImg) " +
//            "FROM OrderDetail od " +
//            "JOIN od.product p " +
//            "JOIN p.jewelry j " +
//            "JOIN j.category c " +
//            "GROUP BY c.categoryId " +
//            "ORDER BY COUNT(DISTINCT od.orderId) DESC")
//    List<CategoryCountDTO> countCategoriesByOrderCount();


//    @Query("SELECT new com.example.DiamondShopSystem.dto.CategoryCountDTO(c.categoryId, c.categoryName ,COUNT(DISTINCT od.orderId), c.categoryImg) " +
//            "FROM OrderDetail od " +
//            "JOIN od.product p " +
//            "JOIN p.jewelry j " +
//            "JOIN j.category c " +
//            "GROUP BY c.categoryId, c.categoryName, c.categoryImg " +
//            "HAVING COUNT(DISTINCT od.orderId) = (SELECT COUNT(DISTINCT od1.orderId) " +
//            "                                     FROM OrderDetail od1 " +
//            "                                     JOIN od1.product p1 " +
//            "                                     JOIN p1.jewelry j1 " +
//            "                                     JOIN j1.category c1 " +
//            "                                     GROUP BY c1.categoryId, c1.categoryName, c1.categoryImg " +
//            "                                     ORDER BY COUNT(DISTINCT od1.orderId) DESC " +
//            "                                     LIMIT 1)")
//    CategoryCountDTO findCategoryWithMaxOrderCount();
}
