package com.kuoning.springbootmall.dao;

import com.kuoning.springbootmall.dto.UserRegisterRequest;
import com.kuoning.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);
    Integer createUser(UserRegisterRequest userRegisterRequest);
}
