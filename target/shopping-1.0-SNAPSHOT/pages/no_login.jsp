<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: YAAANNN
  Date: 2021/12/14
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="top-menu">
    <table id="not-in-menu" align="right">
        <tr>
            <td><a href="<c:url value="/index.jsp"/>">商城首页</a></td>
            <td><a href="<c:url value="/pages/login.html"/>">登录</a></td>
            <td><a href="<c:url value="/pages/register.html"/>">注册</a></td>
        </tr>
    </table>
</div>
</body>
</html>
