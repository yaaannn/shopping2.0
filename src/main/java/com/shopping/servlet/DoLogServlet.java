package com.shopping.servlet;

import com.shopping.po.User;
import com.shopping.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DoLogServlet", value = "/DoLogServlet")
public class DoLogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String captcha1 = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String captcha2 = (String) session.getAttribute("captcha");

        UserService userService = new UserService();
        User currentUserLog = userService.userLog(username, password);

        if (!captcha2.equals(captcha1)) {
            out.println("<h1>验证码错误</h1>");
            // response.sendRedirect("login.jsp");index.jsp为登录页面
            request.getRequestDispatcher("login.jsp").include(request, response);
            return;
        } else {
            if (currentUserLog.getUsername().equals(username) &&
                    currentUserLog.getPassword().equals(password)) {
                // response.sendRedirect("index.jsp");
                out.println("欢迎");
                session.setAttribute("user",currentUserLog);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                out.println("<h1>用户名或密码错误</h1>");
                // response.sendRedirect("login.jsp");index.jsp为登录页面
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
