package com.example.bluevelvetmusicstore.service.product;

import com.example.bluevelvetmusicstore.mappers.ProductMapper;
import com.example.bluevelvetmusicstore.model.entities.Category;
import com.example.bluevelvetmusicstore.model.entities.Dimensions;
import com.example.bluevelvetmusicstore.model.entities.Image;
import com.example.bluevelvetmusicstore.model.entities.Product;
import com.example.bluevelvetmusicstore.model.vo.*;
import com.example.bluevelvetmusicstore.repository.CategoryRepository;
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
  private final CategoryRepository categoryRepository;
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

  @Override
  public ProductDetailsVO retrieveProductById(Long id) {
    Product p = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Este produto não existe!"));
    List<Image> productImages = p.getImages();
    List<ImageDetailsVO> productImagesVO = productImages.stream().map(image -> new ImageDetailsVO(image.getId(), image.getUrl(), image.getIsPrincipal())).toList();
    return new ProductDetailsVO(p.getId(), p.getName(), p.getShortDescription(), p.getFullDescription(), p.getCategory().getName(), p.getBrand(), p.getPrice(), p.getDiscount(), p.getIsActive(), p.getStockAmount(), p.getCost(), p.getDimensions(), productImagesVO);
  }

  @Override
  public DetailProductVO save(CreateProductVO data) {
    Category category =
            categoryRepository
                    .findByName(data.category())
                    .orElseThrow(() -> new RuntimeException("Invalid category!"));
    Product product =
            new Product(
                    data.name(),
                    data.shortDescription(),
                    data.fullDescription(),
                    category,
                    data.brand(),
                    data.price(),
                    data.discount(),
                    data.active(),
                    data.stock(),
                    data.cost(),
                    new Dimensions(data.length(), data.width(), data.height(), data.weight()));
    Product savedProduct = productRepository.save(product);
    List<Image> images =
            data.images().stream()
                    .map(
                            img ->
                                    Image.builder()
                                            .url(img.url())
                                            .description(img.description())
                                            .isPrincipal(img.isPrincipal())
                                            .product(savedProduct)
                                            .build())
                    .toList();
    imageRepository.saveAll(images);
    return new DetailProductVO(savedProduct.getId(), savedProduct.getName());
  }
}
