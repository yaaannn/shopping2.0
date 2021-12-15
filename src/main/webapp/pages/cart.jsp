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
        function del() {
            if (!confirm("确认要删除？")) {
                window.event.returnValue = false
            }
        }
    </script>
    <style>
        hr{
            border-color: #FF7F00;
        }

        table { border-collapse:collapse; }
        input { font-size:12px; }

        a:link, a:visited { text-decoration: none;}
        a:hover, a:active { text-decoration: none;}

        #shopping {text-align:center; margin:0 auto;width: 80%}
        #shopping table { width:80%; line-height:24px; border-top:2px solid #FF7F00; border-bottom:2px solid #FF7F00; }
        #shopping table th { background:#FFE7BA; color:#FF7F00; }
        #shopping table td { padding:10px 0; border-top:1px solid #FF7F00; }
        #shopping table td.thumb img { border:1px solid #dfdfe0; margin:0 10px; vertical-align:middle; }
        #shopping table td.price,
        #shopping table td.number { text-align:center; width:100px; border-left:1px solid #e1e1e1; }
        #shopping table td.price { color:#cc3300; font-weight:bold; }
        #shopping table td.delete { width:60px; text-align:center; border-left:1px solid #e1e1e1; }
        #shopping table td.number span {padding:0 2px;border:1px #c0c0c0 solid;cursor:pointer;}

    </style>
</head>
<body>
<h1>我的购物车</h1>
<a href="../index.jsp">首页</a> >> <a href="../index.jsp">商品列表</a>
<hr>
<div id="shopping">
    <form action="" method="">
        <table border="1">
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
                    <td class="thumb"><img class="cart-pic" src="../static/images/${good1.picture}" alt=""/> ${good1.name}
                    </a></td>
                    <td class="number">${good1.price}</td>
                    <td class="price" id="price_id_1">
                        <span>${good1.price*good2.get(good1)}</span>
                        <input type="hidden" value=""/>
                    </td>
                    <td class="number">${good2.get(good1)}</td>
                    <td class="delete">
                        <a href="CartServlet?action=delete&id=${good1.id}" onclick="del();">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div>
            <span id="total">总计：<%=cart.getTotalPrice() %>￥</span>
        </div>

        </c:if>

        <a href="OrderServlet?action=genOrder">下订单</a>
<%--        <a href="OrderServlet?action=showUserOrder">查看订单</a>--%>
    </form>
</div>
</body>
</html>
