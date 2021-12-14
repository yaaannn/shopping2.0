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
        function success_show(id)
        {
            let num =  document.getElementById("number").value;
            layer.open({
                type: 2,
                title: '购买成功',
                shadeClose: true,
                shade: false,
                maxmin: true, //开启最大化最小化按钮
                area: ['500px', '500px'],
                content: 'CartServlet?id='+id+'&num='+num+'&action=add'
            });
        }

        function add()
        {
            let num = parseInt(document.getElementById("number").value);
            if(num<100)
            {
                document.getElementById("number").value = ++num;
            }
        }

        function sub()
        {
            let num = parseInt(document.getElementById("number").value);
            if(num>1)
            {
                document.getElementById("number").value = --num;
            }
        }

    </script>
    <style type="text/css">

    </style>
</head>
<body>
<h1>商品详情</h1>
<hr>
<div style="text-align: center;">
    <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <!-- 商品详情 -->
            <%
                GoodService goodService = new GoodService();
                Good good = goodService.getGoodById(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("good", good);
            %>
            <td width="70%" valign="top">
                <table>
                    <tr>
                        <td rowspan="4"><img src="static/images/${good.picture}" width="200" height="160" alt="${good.name}"/></td>
                    </tr>
                    <tr>
                        <td><B>${good.name}</B></td>
                    </tr>
                    <tr>
                        <td>产地：${good.city}</td>
                    </tr>
                    <tr>
                        <td>价格：${good.price}￥</td>
                    </tr>
                    <tr>
                        <td>购买数量：<span id="sub" onclick="sub();">-</span><input type="text" id="number" name="number" value="1" size="2"/><span id="add" onclick="add();">+</span></td>
                    </tr>
                </table>
                <div id="cart">
                    <img src="images/buy_now.png" alt=""><a href="javascript:success_show(${good.id})"><img src="images/in_cart.png" alt=""></a><a href="CartServlet?action=show"><img src="images/view_cart.jpg" alt=""/></a>
                </div>
            </td>

            <!-- 浏览过的商品 -->
            <%
                //从客户端获得Cookies集合
                String list = "";
                Cookie[] cookies = request.getCookies();
                //遍历这个Cookies集合"ListViewCookie"
                if (cookies != null && cookies.length > 0) {
                    for (Cookie c : cookies) {
                        if (c.getName().equals("listViewCookie")) {
                            list = URLDecoder.decode(c.getValue(), StandardCharsets.UTF_8);
                        }
                    }
                }
                //加上刚刚浏览的这个商品的id
                list += request.getParameter("id") + ",";
                //如果浏览记录超过1000条，清零
                String[] arr = list.split(",");
                //如果浏览记录超过100条，清零
                if (arr.length > 0) {
                    if (arr.length >= 100)
                        list = "";
                }
                //写回到cookie中
                Cookie cookie = new Cookie("listViewCookie", URLEncoder.encode(list, StandardCharsets.UTF_8));
                response.addCookie(cookie);
            %>
            <td width="30%" bgcolor="#EEE" align="center">
                <br>
                <b>您浏览过的商品</b><br>
                <!-- 循环开始 -->
                <%
                    List<Good> goodList = goodService.getViewList(list);
                    request.setAttribute("goodList", goodList);
                %>
                <c:forEach var="goodList" items="${requestScope.goodList}">
                    <div>
                        <a href="details.jsp?id=${goodList.id}">
                            <img  alt="${goodList.name}" title="${goodList.name}"
                                 src="images/${goodList.picture}" width="120" height="90"/>
                        </a><br>
                        <a style="color: blue">${goodList.name}</a><br>
                        <a>产地:${goodList.city}&nbsp;&nbsp;价格:${goodList.price}￥</a>
                    </div>
                </c:forEach>
                <!-- 循环结束 -->
            </td>
        </tr>
    </table>
</div>
</body>
</html>
