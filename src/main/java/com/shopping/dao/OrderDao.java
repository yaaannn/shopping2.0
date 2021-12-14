package com.shopping.dao;

import com.shopping.po.Order;
import com.shopping.po.OrderItem;
import com.shopping.po.User;
import com.shopping.util.DbUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public  void addOrder(Order order, User user) {
//        Connection connection = DbUtil.getConn();
        String sql = "insert into `order` (id,user_id,state,order_id,number,price) values (null,?,?,?,?,?)";
        String[] args = new String[]{String.valueOf(user.getId()),order.getState(), order.getOrderId(), order.getNumber(), order.getPrice()};
        DbUtil.execUpdate(sql, args);
//        List<OrderItem> orderItemList = order.getOrderItemList();
//        if (orderItemList != null && orderItemList.size() > 0) {
//            String sql2 = "insert into orderitem (id,good_id,order_id,number,price) values (null,?,?,?,?)";
//            for (int i = 0; i < orderItemList.size(); i++) {
//                OrderItem orderItem = orderItemList.get(i);
//                String[] args2 = new String[]{orderItem.getNumber(), orderItem.getPrice(), String.valueOf(order.getId()), String.valueOf(orderItem.getGood().getId())};
//                DbUtil.execUpdate(sql2, args2);
//            }
//        }
    }



    public List<Order> findOrderByUserId(String id) {
        String sql = "select * from `order` where user_id = ? ";
        ResultSet rs = DbUtil.execQuery(sql, new String[]{id});
        List<Order> list = new ArrayList<Order>();

        try {
            while (rs.next()) {
                Order order = new Order();
                order.setId(Integer.parseInt(rs.getString("id")));
                order.setOrderId(rs.getString("order_id"));
                order.setState(rs.getString("state"));
                order.setNumber(rs.getString("number"));
                order.setPrice(rs.getString("price"));
                list.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            DbUtil.closeAll();
        }
        return list;
    }



    public static void main(String[] args) {
        OrderDao orderDao = new OrderDao();
        List<Order> list = new ArrayList<Order>();
        list = orderDao.findOrderByUserId("1");
        for (Order i: list) {
            System.out.println(i.getNumber());
        }
    }
}
