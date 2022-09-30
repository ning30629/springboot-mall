package com.kuoning.springbootmall.service.impl;

import com.kuoning.springbootmall.dao.ProductDao;
import com.kuoning.springbootmall.model.Product;
import com.kuoning.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
