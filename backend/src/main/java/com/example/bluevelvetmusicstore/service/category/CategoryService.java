package com.example.bluevelvetmusicstore.service.category;

import com.example.bluevelvetmusicstore.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
}
