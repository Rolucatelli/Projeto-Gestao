package com.example.bluevelvetmusicstore.service.product;

import com.example.bluevelvetmusicstore.model.Image;
import com.example.bluevelvetmusicstore.model.ImageDetailsVO;
import com.example.bluevelvetmusicstore.model.Product;
import com.example.bluevelvetmusicstore.model.ProductDetailsVO;
import com.example.bluevelvetmusicstore.model.Image;
import com.example.bluevelvetmusicstore.model.Product;
import com.example.bluevelvetmusicstore.repository.ImageRepository;
import com.example.bluevelvetmusicstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        List<Image> productImages = product.getImages();
        imageRepository.deleteAllInBatch(productImages);
        productRepository.deleteById(id);
    }

    @Override
    public ProductDetailsVO retrieveProductById(Long id) {
        Product p = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Este produto não existe!"));
        List<Image> productImages = p.getImageList();
        List<ImageDetailsVO> productImagesVO = productImages.stream().map(image -> new ImageDetailsVO(image.getId(), image.getUrl(), image.getIsPrincipal())).toList();
        return new ProductDetailsVO(p.getId(), p.getName(), p.getShortDescription(), p.getFullDescription(), p.getCategory().getName(), p.getBrand(), p.getPrice(), p.getDiscount(), p.getIsActive(), p.getStockAmount(), p.getCost(), p.getDimensions(), productImagesVO);
    }
}
