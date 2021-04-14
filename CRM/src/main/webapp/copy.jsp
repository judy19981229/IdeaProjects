<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/4/13
  Time: 16:36
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
        <script src="static/jquery-3.6.0.min.js"></script>
        <script type="text/javascript">
            $.ajax({
                url:"",
                data:{

                },
                type:"",
                dataType:"json",
                success:function(param){

                }
            });
        </script>
    </body>
</html>
