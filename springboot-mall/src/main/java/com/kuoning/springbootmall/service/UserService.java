package com.kuoning.springbootmall.service;

import com.kuoning.springbootmall.dto.UserLoginRequest;
import com.kuoning.springbootmall.dto.UserRegisterRequest;
import com.kuoning.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);
}
