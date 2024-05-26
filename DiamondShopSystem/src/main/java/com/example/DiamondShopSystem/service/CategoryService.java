package com.example.DiamondShopSystem.service;

import com.example.DiamondShopSystem.model.Category;
import com.example.DiamondShopSystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category newCategory) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setCategoryName(newCategory.getCategoryName());
                    return categoryRepository.save(existingCategory);
                }).orElseGet(() -> {
                    newCategory.setCategoryId(id);
                    return categoryRepository.save(newCategory);
                });
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
