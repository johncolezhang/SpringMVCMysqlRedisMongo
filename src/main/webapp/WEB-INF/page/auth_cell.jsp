<%--
  Created by IntelliJ IDEA.
  User: johncole
  Date: 2017/7/7
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>短信验证码验证</title>
</head>
<body>
<h1>手机号码：${requestScope.cell}</h1>
<form action="/send/cell_auth" method="post">
                <input type="hidden" name="cell" id="cell" value="${requestScope.cell}"/>
    输入验证码: <input type="text" name="auth_code" id="auth_code"/>
    <input type="submit" value="Submit">
</form>
</body>
</html>
