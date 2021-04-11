<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/3/13
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <center>
        <font style="color:red;font-size: 50px">
            考试成绩为：<%=request.getAttribute("score")%>
        </font>
    </center>
</body>
</html>
