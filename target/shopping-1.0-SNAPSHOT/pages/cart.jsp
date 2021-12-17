<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.shopping.po.Cart" %>
<%@ page import="com.shopping.po.Good" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: YAAANNN
  Date: 2021/12/11
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>
<html>
<head>
    <title>Title</title>
    <script>
        // function del() {
        //     if (!confirm("确认要删除？")) {
        //         window.event.returnValue = false
        //     }
        // }
    </script>
</head>
<body>
<c:if test="${empty sessionScope.user}">
    <%@ include file="../pages/no_login.jsp" %>
</c:if>

<c:if test="${!empty sessionScope.user}">
    <%@ include file="../pages/is_login.jsp" %>
</c:if>
<h1 style="text-align: center">我的购物车</h1>
<hr style="border-color: #FF7F00;">
<div id="shopping">
    <%--    <form action="" method="">--%>
    <table>
        <tr>
            <th>商品名称</th>
            <th>商品单价</th>
            <th>商品价格</th>
            <th>购买数量</th>
            <th>操作</th>
        </tr>
        <c:if test="${!empty sessionScope.cart}">
        <%
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            HashMap<Good, Integer> goods = cart.getGoods();
            Set<Good> good = goods.keySet();
            request.setAttribute("good2", goods);
            request.setAttribute("good1", good);
        %>
        <c:forEach items="${requestScope.good1}" var="good1">
            <tr name="products" id="product_id_1">
                <td class="thumb"><img class="cart-pic" src="../static/images/${good1.picture}" alt=""/><span
                        style="width:180px; display:inline-block"></span>${good1.name}
                    </a></td>
                <td class="number">${good1.price}</td>
                <td class="price" id="price_id_1">
                    <span>${good1.price*good2.get(good1)}</span>
                    <input type="hidden" value=""/>
                </td>
                <td class="number">${good2.get(good1)}</td>
                <td class="delete">
                    <a href="CartServlet?action=delete&id=${good1.id}" onclick="
                    if (!confirm('确认要删除？')) {
                        window.event.returnValue = false
                    }">删除</a>
                </td></tr>
        </c:forEach>
    </table>

    <div>
        <span style="font-size: 25px;" id="total">总计：<%=cart.getTotalPrice() %>￥</span>
    </div>

    </c:if>

    <a style="font-size: 25px;float: right" href="<c:url value="/OrderServlet?action=genOrder"/>">下订单</a>
</div>
</body>
</html>
