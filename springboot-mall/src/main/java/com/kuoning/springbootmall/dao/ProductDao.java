package com.kuoning.springbootmall.dao;

import com.kuoning.springbootmall.dto.ProductRequest;
import com.kuoning.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);
}
