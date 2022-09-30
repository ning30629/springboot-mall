package com.kuoning.springbootmall.dao;

import com.kuoning.springbootmall.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
