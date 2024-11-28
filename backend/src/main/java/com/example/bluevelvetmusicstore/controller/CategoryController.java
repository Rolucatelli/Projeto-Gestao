package com.example.bluevelvetmusicstore.controller;

import com.example.bluevelvetmusicstore.model.vo.CategoryListVO;
import com.example.bluevelvetmusicstore.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category/v1")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping("/all")
  public ResponseEntity<CategoryListVO> getAllCategories() {
    CategoryListVO response = categoryService.getCategories();
    return ResponseEntity.ok(response);
  }
}
