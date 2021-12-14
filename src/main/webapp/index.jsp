<%@ page import="com.shopping.po.Good" %>
<%@ page import="com.shopping.service.GoodService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.shopping.po.User" %>
<%--
  Created by IntelliJ IDEA.
  User: YAAANNN
  Date: 2021/12/4
  Time: 19:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ include file="head.jsp" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        #out {
            width: 900px;
            border: 4px solid red;
            margin: 0 auto;
            overflow: hidden;
        }

        #out div {
            text-align: center;
            width: 200px;
            height: 150px;
            margin: 10px;
            float: left;
        }

        #name {
            font-size: 10pt;
            color: blue;
        }

        img {
            width: 120px;
            height: 90px;
        }
    </style>
</head>
<body>
<c:if test="${empty sessionScope.user}">
    <div id="top-menu">
        <table id="not-in-menu" align="right">
            <tr>
                <td><a href="login.html">登录</a></td>
                <td><a href="register.html">注册</a></td>
            </tr>
        </table>
    </div>
</c:if>

<c:if test="${!empty sessionScope.user}">
    <div id="top-menu">
        <table id="in-menu" align="right">
            <tr>
<%--                <td><a href="index.jsp">商城首页</a></td>--%>
                <td><span>欢迎, <span style="color: red;font-weight: bold;">${sessionScope.user.username}!</span> </span>
                </td>
    &nbsp;&nbsp;

                    <%--                <td><a href="userServlet?action=logout" id="user-logout">注销</a></td>--%>
                <td><a href="cart.jsp">购物车</a>
                </td>
    &nbsp;&nbsp;
                <td><a href="OrderServlet?action=showUserOrder">我的订单</a></td>
            </tr>
        </table>
    </div>
</c:if>

<h1>商品展示</h1>
<%--<% User user = (User) session.getAttribute("user");%>--%>
<%--<c:if test="${user!=null}">--%>
<%--    <%=user.getUsername()%>--%>
<%--</c:if>--%>

<hr>
<%--<a href="login.html">登录</a>--%>
<%
    GoodService goodService = new GoodService();
    List<Good> goods = goodService.getAllGoods();
    request.setAttribute("goods", goods);
%>

<div id="out">
    <c:forEach var="goods" items="${requestScope.goods}">

        <div>
            <a href="details.jsp?id=${goods.id}">
                <img alt="${goods.name}" title="${goods.name}" src="static/images/${goods.picture}"/>
            </a><br>
            <a id="name">${goods.name}</a><br>
            <a>产地:${goods.city}&nbsp;&nbsp;价格:${goods.price}￥</a>
        </div>

    </c:forEach>
</div>

</body>
</html>
