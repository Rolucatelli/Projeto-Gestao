package com.example.bluevelvetmusicstore.service.category;

import com.example.bluevelvetmusicstore.model.entities.Category;
import com.example.bluevelvetmusicstore.model.vo.CategoryListVO;
import com.example.bluevelvetmusicstore.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;

  @Override
  public CategoryListVO getCategories() {
    List<Category> categories = categoryRepository.findAll();
    List<String> names = categories.stream().map(Category::getName).toList();
    return new CategoryListVO(names);
  }
}
