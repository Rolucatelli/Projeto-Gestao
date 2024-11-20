package com.example.bluevelvetmusicstore.service.product;

import com.example.bluevelvetmusicstore.mappers.ProductMapper;
import com.example.bluevelvetmusicstore.model.entities.Image;
import com.example.bluevelvetmusicstore.model.entities.Product;
import com.example.bluevelvetmusicstore.model.vo.ProductDashboardVO;
import com.example.bluevelvetmusicstore.repository.ImageRepository;
import com.example.bluevelvetmusicstore.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final ImageRepository imageRepository;

  @Override
  public Page<ProductDashboardVO> retrieveAllProducts(String search, Pageable pageable) {
    Page<Product> productsPage =
        Strings.isNotBlank(search)
            ? productRepository.searchProducts(search, pageable)
            : productRepository.findAll(pageable);

    List<ProductDashboardVO> productDashboardVOList =
        productsPage.stream().map(productMapper::entityToDashVO).toList();

    return new PageImpl<>(productDashboardVOList, pageable, productsPage.getTotalElements());
  }

  @Override
  public void deleteProduct(Long id) {
    Product product =
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    List<Image> productImages = product.getImages();
    imageRepository.deleteAllInBatch(productImages);
    productRepository.deleteById(id);
  }
}
