<%--
  Created by IntelliJ IDEA.
  User: YAAANNN
  Date: 2021/12/11
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="text-align: center;">
    <img src="images/add_cart_success.jpg"/>
    <hr>
    <%
        String id = request.getParameter("id");
        String num = request.getParameter("num");
    %>
    您成功购买了<%=num%>件商品编号为<%=id%>的商品&nbsp;&nbsp;&nbsp;&nbsp;
    <br>
    <br>
    <br>

</div>
</body>
</html>
