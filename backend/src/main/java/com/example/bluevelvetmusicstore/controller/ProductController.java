package com.example.bluevelvetmusicstore.controller;

import com.example.bluevelvetmusicstore.model.Product;
import com.example.bluevelvetmusicstore.model.ProductDetailsVO;
import com.example.bluevelvetmusicstore.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product/v1")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsVO> retrieveProductById(@PathVariable Long id) {
        ProductDetailsVO product = productService.retrieveProductById(id);
        return ResponseEntity.ok(product);
    }
}
