package com.example.bluevelvetmusicstore.service.product;

import com.example.bluevelvetmusicstore.model.Product;
import com.example.bluevelvetmusicstore.model.ProductDetailsVO;

public interface ProductService {
    public ProductDetailsVO retrieveProductById(Long id);
    public void deleteProduct(Long id);
}
