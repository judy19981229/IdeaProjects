<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/4/26
  Time: 10:31
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
        <title>Title</title>
        <base href="<%=basePath%>">
    </head>
    <body>

    </body>
</html>
