package com.kuoning.springbootmall.dao;

import com.kuoning.springbootmall.dto.ProductQueryParams;
import com.kuoning.springbootmall.dto.ProductRequest;
import com.kuoning.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);


    Integer countProduct(ProductQueryParams productQueryParams);
}
