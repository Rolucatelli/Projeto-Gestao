package com.example.bluevelvetmusicstore.service.category;

import com.example.bluevelvetmusicstore.dao.category.CategoryDao;
import com.example.bluevelvetmusicstore.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }


    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryDao.save(category);
    }
}
