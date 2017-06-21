<%--
  Created by IntelliJ IDEA.
  User: johncole
  Date: 2017/6/21
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form action="/file/upload" method="post" enctype="multipart/form-data">
        File: <input type="file" name="file"/>
        Desc: <input type="text" name="" id="desc"/>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
