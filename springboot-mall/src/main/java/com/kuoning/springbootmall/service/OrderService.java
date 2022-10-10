package com.kuoning.springbootmall.service;

import com.kuoning.springbootmall.dto.CreateOrderRequest;
import com.kuoning.springbootmall.dto.OrderQueryParams;
import com.kuoning.springbootmall.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);
    List<Order> getOrders(OrderQueryParams orderQueryParams);
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
