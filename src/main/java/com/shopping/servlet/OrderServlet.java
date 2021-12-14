package com.shopping.servlet;

import com.shopping.po.*;
import com.shopping.service.OrderService;
import com.shopping.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
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
//            request.setAttribute("message", "请先登录，2秒后将自动跳转到登录页面！<meta http-equiv='Refresh' content='2;URL="+request.getContextPath()+"/client/login.jsp'>");
//            request.getRequestDispatcher("/client/message.jsp").forward(request, response);
            return;
        }

        String order_id = StringUtil.generateStr();//自动生成：订单号
        //如果已登录，则从session中取出购物车中商品：Cart  Map<String,CartItem>  填充模型
        Cart cart = (Cart) session.getAttribute("cart");//购物车
        HashMap<Good, Integer> goods = cart.getGoods();
        Set<Good> good = goods.keySet();
        Order order = new Order();//订单
//        orders.setId(id);
//        Integer number = (Integer) request.getAttribute("number");
        order.setState("0");
        order.setNumber(null);
        order.setPrice(String.valueOf(cart.getTotalPrice()));
//        orders.setState(0);
        order.setOrderId(order_id);
        //购物项
        user1.setId(user.getId()+1);
        System.out.println(user1.getId());
//        List<OrderItem> orderItem = new ArrayList<OrderItem>();

        orderService.addOrder(order,user1);
        session.removeAttribute("cart");//付款后，清空session中的购物车
//        request.setAttribute("message", "付款成功，请等待店家发货！<span style='font-size: 18px;'><br/><br/>也可点击“我的订单”，查看您的订单信息</span>");//中间的付款步骤没写，这里只是模拟了购物的流程
        request.getRequestDispatcher("/client/message.jsp").forward(request, response);
    }

    private void showUserOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null){
            request.setAttribute("message", "请先登录，2秒后将自动跳转到登录页面！<meta http-equiv='Refresh' content='2;URL="+request.getContextPath()+"/client/login.jsp'>");
            request.getRequestDispatcher("/client/message.jsp").forward(request, response);
            return;
        }
        List<Order> orderList = orderService.findOrderByUserId(String.valueOf(user.getId()+1));//查询某个用户的所有订单
        for (Order or: orderList) {
            System.out.println(or);
        }
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
