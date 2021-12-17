<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.shopping.service.GoodService" %>
<%@ page import="com.shopping.po.Good" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%--
  Created by IntelliJ IDEA.
  User: YAAANNN
  Date: 2021/12/9
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript">
        function success_show(id) {
            let num = document.getElementById("number").value;
            layer.open({
                type: 2,
                title: '购买成功',
                shadeClose: true,
                shade: false,
                maxmin: true, //开启最大化最小化按钮
                area: ['500px', '500px'],
                content: '/CartServlet?id=' + id + '&num=' + num + '&action=add'
            });
        }

        function add() {
            let num = parseInt(document.getElementById("number").value);
            if (num < 100) {
                document.getElementById("number").value = ++num;
            }
        }

        function sub() {
            let num = parseInt(document.getElementById("number").value);
            if (num > 1) {
                document.getElementById("number").value = --num;
            }
        }

    </script>
</head>
<body>
<c:if test="${empty sessionScope.user}">
    <%@ include file="../pages/no_login.jsp" %>
</c:if>

<c:if test="${!empty sessionScope.user}">
    <%@ include file="../pages/is_login.jsp" %>
</c:if>
<h1 style="text-align: center">商品详情</h1>
<hr>
<div style="text-align: center;">
    <table width="1000" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td width="20"></td>
            <!-- 商品详情 -->
            <%
                GoodService goodService = new GoodService();
                Good good = goodService.getGoodById(
                        Integer.parseInt(request.getParameter("id")));
                request.setAttribute("good", good);
            %>
            <td width="50%" valign="top">
                <table>
                    <tr><td rowspan="4"><img class="detail-pic" src="../static/images/${good.picture}" alt=""/></td></tr>
                    <tr><td class="item-name"><b>${good.name}</b></td></tr>
                    <tr><td>产地：${good.city}</td></tr>
                    <tr><td class="price">价格：${good.price}￥</td></tr>
                    <tr><td>
                            购买数量：
                            <span id="sub" onclick="sub();"><a> ➖ </a> </span>
                            <label for="number"></label><input type="text" id="number" value="1" size="2"/>
                            <span id="add" onclick="add();"><a> ➕ </a> </span>
                        </td></tr>
                </table>
                <div id="cart">
                    <img src="../static/images/buy_now.png" alt=""/>
                    <a
                            href="javascript:success_show(${good.id})">
                        <img src="../static/images/in_cart.png" alt=""/>
                    </a>
                    <a
                            href="<c:url value="/CartServlet?action=show"/>"><img
                            src="<c:url value="/static/images/view_cart.jpg"/>" alt=""/>
                    </a>
                </div>
            </td>

            <!-- 浏览过的商品 -->
            <%
                String list = "";
                Cookie[] cookies = request.getCookies();
                if (cookies != null && cookies.length > 0) {
                    for (Cookie c : cookies) {
                        if (c.getName().equals("listViewCookie")) {
                            list = URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8); } } }
                list += request.getParameter("id") + ",";
                String[] arr = list.split(",");
                if (arr.length > 0) {
                    if (arr.length >= 100)
                        list = ""; }
                Cookie cookie = new Cookie("listViewCookie", URLEncoder.encode(list, StandardCharsets.UTF_8));
                response.addCookie(cookie);
                List<Good> goodList = goodService.getViewList(list);
                request.setAttribute("goodList", goodList);
            %>
            <td width="30%" bgcolor="#EEE" align="center">
                <br>
                <b>您浏览过的商品</b><br>
                <c:forEach var="goodList" items="${requestScope.goodList}">
                    <div>
                        <a href="details.jsp?id=${goodList.id}">
                            <img alt="${goodList.name}" title="${goodList.name}"
                                 src="../static/images/${goodList.picture}" width="120" height="90"/>
                        </a><br>
                        <a style="color: blue">${goodList.name}</a><br>
                        <a>产地:${goodList.city}&nbsp;&nbsp;价格:${goodList.price}￥</a>
                    </div>
                </c:forEach>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
