package com.example.DiamondShopSystem.controller;

import com.example.DiamondShopSystem.model.Category;
import com.example.DiamondShopSystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/customer/categories")
    public List<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/adminsale/categories/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping("/admin/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping("/admin/categories/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @DeleteMapping("/admin/categories/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping("/admin/categories")
    public ResponseEntity<List<Category>> getAllCategoriesWithDiamonds() {
        List<Category> categories = categoryService.findAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
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
