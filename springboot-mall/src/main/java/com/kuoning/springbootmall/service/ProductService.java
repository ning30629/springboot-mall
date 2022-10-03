package com.kuoning.springbootmall.service;

import com.kuoning.springbootmall.dto.ProductRequest;
import com.kuoning.springbootmall.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
}
