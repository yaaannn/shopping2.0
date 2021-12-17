package com.shopping.servlet;

import com.shopping.po.Cart;
import com.shopping.po.Good;
import com.shopping.service.GoodService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {

    private String action;
    private final GoodService goodService = new GoodService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        // PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("action") != null) {
            this.action = request.getParameter("action");
            if (action.equals("add")) { // 如果是添加商品进购物车
                if (addToCart(request, response)) {
                    request.getRequestDispatcher("pages/success.jsp")
                            .forward(request, response);
                } else {
                    request.getRequestDispatcher("pages/failure.jsp")
                            .forward(request, response);
                }
            }
            if (action.equals("show")) {// 如果是显示购物车
                request.getRequestDispatcher("pages/cart.jsp").forward(request, response);
            }
            if (action.equals("delete")) {// 如果是执行删除购物车中的商品
                if (deleteFromCart(request, response)) {
                    request.getRequestDispatcher("pages/cart.jsp")
                            .forward(request, response);
                } else {
                    request.getRequestDispatcher("pages/cart.jsp")
                            .forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private boolean addToCart(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String number = request.getParameter("num");
        request.setAttribute("number",number);
        Good good = goodService.getGoodById(Integer.parseInt(id));
        // 是否是第一次给购物车添加商品,需要给session中创建一个新的购物车对象
        if (request.getSession().getAttribute("cart") == null) {
            Cart cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        return cart.addGoodsInCart(good, Integer.parseInt(number));
    }
    // 从购物车中删除商品
    private boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Good good = goodService.getGoodById(Integer.parseInt(id));
        System.out.println("aaa" + good.getName());
        return cart.removeGoodsFromCart(good);
    }
}
