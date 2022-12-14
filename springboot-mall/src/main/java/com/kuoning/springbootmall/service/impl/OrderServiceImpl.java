package com.kuoning.springbootmall.service.impl;

import com.kuoning.springbootmall.dao.OrderDao;
import com.kuoning.springbootmall.dao.ProductDao;
import com.kuoning.springbootmall.dao.UserDao;
import com.kuoning.springbootmall.dto.BuyItem;
import com.kuoning.springbootmall.dto.CreateOrderRequest;
import com.kuoning.springbootmall.dto.OrderQueryParams;
import com.kuoning.springbootmall.model.Order;
import com.kuoning.springbootmall.model.OrderItem;
import com.kuoning.springbootmall.model.Product;
import com.kuoning.springbootmall.model.User;
import com.kuoning.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);

        for (Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemsById(order.getOrderId());

            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    @Override
    public Order getOrderById(Integer orderId) {
        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsById(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        //檢查 user 是否存在
        User user = userDao.getUserById(userId);

        if (user == null) {
            log.warn ("該 userId {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        double totalAmount = 0;
        List<String> countCategory = new ArrayList<>();
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            Product product = productDao.getProductById(buyItem.getProductId());

            //檢查 product 是否存在、庫存是否足夠
            if (product == null) {
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("商品 {} 庫存不足，無法購買。剩餘庫存 {} ，欲購買數量 {}",
                        buyItem.getProductId(), product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            //扣除商品庫存
            productDao.updateStock(product.getProductId(), product.getStock()-buyItem.getQuantity());

            //取出購買商品的種類
            String buyProductCategory = productDao.getProductCategory(buyItem.getProductId());
            countCategory.add(buyProductCategory);

            //計算總價錢(原價)
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;

            //轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }
        //檢查總共購買了幾類商品
        List<String> totalCategory = new ArrayList();
        for (int i = 0; i < countCategory.size(); i++) {
            if (!totalCategory.contains(countCategory.get(i))) {
                totalCategory.add(countCategory.get(i));
            }
        }
        double discount = 0;

        //計算折扣後的價錢 (購買三個種類商品打8折，兩個種類商品打9折，單一種類不打折)
        if (totalCategory.size()==3 ){
            discount = 0.8;
        } else if (totalCategory.size()==2) {
            discount = 0.9;
        } else {
            discount = 1;
        }

        totalAmount = totalAmount * discount;
        //創建訂單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
