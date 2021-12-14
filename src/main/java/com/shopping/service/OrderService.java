package com.shopping.service;

import com.shopping.dao.OrderDao;
import com.shopping.po.Order;
import com.shopping.po.User;

import java.util.List;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService() {
        orderDao = new OrderDao();
    }

    public void addOrder(Order order, User user) {
        orderDao.addOrder(order, user);
    }

    public List<Order> findOrderByUserId(String id) {
        return orderDao.findOrderByUserId(id);
    }
}
