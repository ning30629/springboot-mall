package com.kuoning.springbootmall.controller;

import com.kuoning.springbootmall.dto.UserLoginRequest;
import com.kuoning.springbootmall.dto.UserRegisterRequest;
import com.kuoning.springbootmall.model.User;
import com.kuoning.springbootmall.service.UserService;
import com.kuoning.springbootmall.util.JwtToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "使用者controller層")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation("使用者註冊")
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @ApiOperation("使用者登入")
    @PostMapping("/users/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        User user = userService.login(userLoginRequest);

        JwtToken jwtToken = new JwtToken();
        String token = jwtToken.generateToken(userLoginRequest); // 取得token

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
