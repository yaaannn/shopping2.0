package com.shopping.servlet;

import com.shopping.po.*;
import com.shopping.service.OrderService;
import com.shopping.service.UserService;
import com.shopping.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    UserService userService = new UserService();
    OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation  = request.getParameter("action");
        if ("showUserOrder".equals(operation)){
            showUserOrder(request,response);
        }
        if("genOrder".equals(operation)){
            genOrder(request,response);
        }
    }

    private void genOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录：如果还没登录，则转向登录页面
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        User user1 =new User();
        if(user == null){
            request.getRequestDispatcher("/pages/login.html").forward(request, response);
            return;
        }
        int userId = userService.getUserId(user.getUsername());
        String order_id = StringUtil.generateStr();
        Cart cart = (Cart) session.getAttribute("cart");
        Order order = new Order();
        order.setState("0");
        order.setNumber(null);
        order.setPrice(String.valueOf(cart.getTotalPrice()));
        order.setOrderId(order_id);
        user1.setId(userId);
        orderService.addOrder(order,user1);
        session.removeAttribute("cart");//付款后，清空session中的购物车
        request.getRequestDispatcher("/CartServlet?action=show").forward(request, response);
    }

    private void showUserOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null){
            request.getRequestDispatcher("/pages/login.html").forward(request, response);
            return;
        }
        int userId = userService.getUserId(user.getUsername());
//        request.setAttribute("userId",userId);
        System.out.println(userId);
        List<Order> orderList = orderService.findOrderByUserId(String.valueOf(userId));//查询某个用户的所有订单

        for (Order or: orderList) {
            System.out.println(or);
        }
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("pages/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
