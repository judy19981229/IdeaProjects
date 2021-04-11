<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/4/6
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
    <head>
        <title>添加学生</title>
        <base href="<%=basePath%>">
    </head>
    <body>
        <center>
            <form action="InsertStudent">
                学生姓名<input type="text" name="name" id="name"><br/>
                学生邮箱<input type="text" name="email" id="email"><br/>
                学生年龄<input type="text" name="age" id="age"><br/>
                <input type="submit" value="注册"><br>
            </form>
        </center>
    </body>
</html>
