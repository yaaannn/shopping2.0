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
<%@ include file="pages/head.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${empty sessionScope.user}">
    <%@ include file="pages/no_login.jsp" %>
</c:if>

<c:if test="${!empty sessionScope.user}">
    <%@ include file="pages/is_login.jsp" %>
</c:if>

<h1 style="text-align: center;">商品展示</h1>

<hr>

<%
    GoodService goodService = new GoodService();
    List<Good> goods = goodService.getAllGoods();
    request.setAttribute("goods", goods);
%>

<div class="out">
    <c:forEach var="goods" items="${requestScope.goods}">

        <div class="item-div">
            <a href="pages/details.jsp?id=${goods.id}">
                <img class="item-pic" alt="" src="/static/images/${goods.picture}"/>
            </a><br>
            <a class= "item-name">${goods.name}</a><br>
            <a >产地:${goods.city}</a> &nbsp;&nbsp;<a class= "price">价格:${goods.price}￥</a>
        </div>

    </c:forEach>
</div>

</body>
</html>
