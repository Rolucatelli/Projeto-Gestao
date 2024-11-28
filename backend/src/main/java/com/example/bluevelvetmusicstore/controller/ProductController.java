package com.example.bluevelvetmusicstore.controller;

import com.example.bluevelvetmusicstore.enums.ProductSortField;
import com.example.bluevelvetmusicstore.model.vo.CreateProductVO;
import com.example.bluevelvetmusicstore.model.vo.DetailProductVO;
import com.example.bluevelvetmusicstore.model.vo.ProductDashboardVO;
import com.example.bluevelvetmusicstore.model.vo.ProductDetailsVO;
import com.example.bluevelvetmusicstore.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/v1")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/all")
  public ResponseEntity<Page<ProductDashboardVO>> listAllDashboard(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) String search,
      @RequestParam(defaultValue = "name") ProductSortField sortBy,
      @RequestParam(defaultValue = "asc") String sortDirection) {
    Sort.Direction direction = Sort.Direction.ASC;
    if ("desc".equalsIgnoreCase(sortDirection)) {
      direction = Sort.Direction.DESC;
    }
    Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy.getFieldName()));
    Page<ProductDashboardVO> products = productService.retrieveAllProducts(search, pageable);
    return ResponseEntity.ok(products);
  }

  @PostMapping("/create")
  public ResponseEntity<DetailProductVO> createProduct(
          @RequestBody CreateProductVO createProductVO) {
    DetailProductVO response = productService.save(createProductVO);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDetailsVO> retrieveProductById(@PathVariable Long id) {
    ProductDetailsVO product = productService.retrieveProductById(id);
    return ResponseEntity.ok(product);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
