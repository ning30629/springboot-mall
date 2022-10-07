package com.kuoning.springbootmall.dao;

import com.kuoning.springbootmall.model.Order;
import com.kuoning.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsById(Integer orderId);
    Integer createOrder(Integer userId, int totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}