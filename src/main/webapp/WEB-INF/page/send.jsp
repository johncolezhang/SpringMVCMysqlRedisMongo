<%--
  Created by IntelliJ IDEA.
  User: johncole
  Date: 2017/6/22
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证邮箱及手机号</title>
</head>
<body>
<h1>验证邮箱</h1>
<form action="/send/email_send" method="post">
    Email: <input type="text" name="email" id="email"/>
    <input type="submit" value="Submit">
</form>

<h1>验证手机号</h1>
<form action="/send/cell_send" method="post">
    Cell: <input type="text" name="cell" id="cell"/>
    <input type="submit" value="Submit">
</form>
</body>
</html>
