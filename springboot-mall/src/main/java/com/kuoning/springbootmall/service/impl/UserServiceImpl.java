package com.kuoning.springbootmall.service.impl;

import com.kuoning.springbootmall.dao.UserDao;
import com.kuoning.springbootmall.dto.UserRegisterRequest;
import com.kuoning.springbootmall.model.User;
import com.kuoning.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }


}
