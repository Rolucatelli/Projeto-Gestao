package com.example.bluevelvetmusicstore.controller;

import com.example.bluevelvetmusicstore.model.vo.ProductDashboardVO;
import com.example.bluevelvetmusicstore.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/v1")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/all")
  public ResponseEntity<Page<ProductDashboardVO>> listAllDashboard(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
    Page<ProductDashboardVO> response = productService.retrieveAllProducts(pageable);
    return ResponseEntity.ok(response);
  }
}
