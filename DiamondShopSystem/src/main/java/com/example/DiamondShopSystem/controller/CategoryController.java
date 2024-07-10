package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.dto.CategoryCountDTO;
import com.example.DiamondShopSystem.model.Category;
import com.example.DiamondShopSystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping("/secure/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping("/secure/categories/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/secure/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

//    @GetMapping("/categories/order-count")
//    public ResponseEntity<List<CategoryCountDTO>> getCategoryOrderCounts() {
//        List<CategoryCountDTO> categoryOrderCounts = categoryService.getCategoriesByOrderCount();
//        return new ResponseEntity<>(categoryOrderCounts, HttpStatus.OK);
//    }
//
//
//    @GetMapping("/categories/top-order-count")
//    public ResponseEntity<CategoryCountDTO> getTopCategoryByOrderCount() {
//        CategoryCountDTO topCategory = categoryService.getTopCategoryByOrderCount();
//        return ResponseEntity.ok(topCategory);
//    }
}
