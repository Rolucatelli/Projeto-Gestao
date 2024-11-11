package com.example.bluevelvetmusicstore.dao.category;

import com.example.bluevelvetmusicstore.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    List<Category> findAll();
    Optional<Category> findById(long id);
    Category save(Category category);
}
