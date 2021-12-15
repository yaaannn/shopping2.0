<%--
  Created by IntelliJ IDEA.
  User: YAAANNN
  Date: 2021/12/14
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="top-menu">
    <table id="in-menu" align="right">
        <tr>
            <%--                <td><a href="index.jsp">商城首页</a></td>--%>
            <td><span>欢迎, <span style="color: red;font-weight: bold;">${sessionScope.user.username}!</span> </span>
            </td>
            &nbsp;&nbsp;

            <%--                <td><a href="userServlet?action=logout" id="user-logout">注销</a></td>--%>
            <td><a href="pages/cart.jsp">购物车</a>
            </td>
            &nbsp;&nbsp;
            <td><a href="OrderServlet?action=showUserOrder">我的订单</a></td>
        </tr>
    </table>
</div>
</body>
</html>
