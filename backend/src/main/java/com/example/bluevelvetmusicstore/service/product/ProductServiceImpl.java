package com.example.bluevelvetmusicstore.service.product;

import com.example.bluevelvetmusicstore.mappers.ProductMapper;
import com.example.bluevelvetmusicstore.model.entities.Product;
import com.example.bluevelvetmusicstore.model.vo.ProductDashboardVO;
import com.example.bluevelvetmusicstore.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Override
  public Page<ProductDashboardVO> retrieveAllProducts(Pageable pageable) {
    Page<Product> productsPage = productRepository.findAll(pageable);
    List<ProductDashboardVO> list =
        productsPage.stream().map(productMapper::entityToDashVO).toList();
    return new PageImpl<>(list, pageable, productsPage.getTotalElements());
  }
}
