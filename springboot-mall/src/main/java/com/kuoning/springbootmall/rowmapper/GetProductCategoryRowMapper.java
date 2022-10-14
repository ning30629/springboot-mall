package com.kuoning.springbootmall.rowmapper;

import com.kuoning.springbootmall.constant.ProductCategory;
import com.kuoning.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProductCategoryRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        String categoryStr = rs.getString("category"); //接住resultSet資料庫中取出的值
        ProductCategory category = ProductCategory.valueOf(categoryStr); //字串轉換成enum類型
        product.setCategory(category);

        return product;
    }
}
