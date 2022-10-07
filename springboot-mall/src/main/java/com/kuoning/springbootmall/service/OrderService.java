package com.kuoning.springbootmall.service;

import com.kuoning.springbootmall.dto.CreateOrderRequest;
import com.kuoning.springbootmall.model.Order;

public interface OrderService {

    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
