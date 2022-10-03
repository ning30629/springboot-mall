package com.kuoning.springbootmall.service;

import com.kuoning.springbootmall.constant.ProductCategory;
import com.kuoning.springbootmall.dto.ProductRequest;
import com.kuoning.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


}
