package com.kuoning.springbootmall.controller;

import com.kuoning.springbootmall.dto.CreateOrderRequest;
import com.kuoning.springbootmall.dto.OrderQueryParams;
import com.kuoning.springbootmall.model.Order;
import com.kuoning.springbootmall.service.OrderService;
import com.kuoning.springbootmall.util.JwtToken;
import com.kuoning.springbootmall.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Api(tags = "訂單controller層")
@Validated
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("根據useId查詢訂單")
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<?> getOrders(
            @RequestHeader("Authorization") String au,
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {
        //驗證token
        String token = au.substring(6);
        JwtToken jwtToken = new JwtToken();
        try {
            jwtToken.validateToken(token);
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        //取得order list
        List<Order> orderList = orderService.getOrders(orderQueryParams);

        //取得 order總數
        Integer total = orderService.countOrder(orderQueryParams);

        //分頁
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @ApiOperation("根據useId創建訂單")
    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(
            @RequestHeader("Authorization") String au,
            @PathVariable Integer userId,
            @RequestBody @Valid CreateOrderRequest createOrderRequest) {

        //驗證token
        String token = au.substring(6);
        JwtToken jwtToken = new JwtToken();
        try {
            jwtToken.validateToken(token);
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
