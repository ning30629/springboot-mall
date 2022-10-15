package com.kuoning.springbootmall.rowmapper;

import com.kuoning.springbootmall.constant.ProductCategory;
import com.kuoning.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProductCategoryRowMapper implements RowMapper<String> {

    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {

        String categoryStr = rs.getString("category"); //接住resultSet資料庫中取出的值

        return categoryStr;
    }
}
