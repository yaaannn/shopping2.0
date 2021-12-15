<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: YAAANNN
  Date: 2021/12/12
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${empty sessionScope.user}">
    <%@ include file="../pages/no_login.jsp" %>
</c:if>

<c:if test="${!empty sessionScope.user}">
    <%@ include file="../pages/is_login.jsp" %>
</c:if>
<div>
    <table id="shopping">
        <tr>
            <th>用户ID</th>
            <th>订单ID</th>
            <th>发货状态</th>
            <th>总价</th>
            <th>操作</th>
        </tr>
        <jsp:useBean id="orderList" scope="request" type="java.util.List"/>
        <c:forEach items="${orderList}" var="order">
            <tr><td>${order.userId+1}</td>
                <td>${order.orderId}</td>
                <td><c:if test="${order.state==0}">未发货</c:if></td>
                <td>${order.price}</td>
                <td><a href="">查看详情</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
