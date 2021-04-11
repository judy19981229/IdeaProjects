<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/4/1
  Time: 21:01
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
        <title>DispatcherServlet</title>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="jquery-3.6.0.min.js"></script>
        <script type="text/javascript">
            $(function(){
                $("#btn1").click(function(){
                    $.get("user/returnVoid.do","userName=mike&password=123",rollback,"json");
                    function rollback(param){
                        alert(param.userName);
                        alert(param.password);
                        $("#div1").text("welcome mike");
                    }
                });
                $("#btn2").click(function(){
                    $.get("user/returnUser.do","userName=mike&password=123",rollback,"json");
                    function rollback(param){
                        alert(param.userName);
                        alert(param.password);
                        $("#div2").text("welcome mike");
                    }
                });
                $("#btn3").click(function(){
                    $.get("user/returnList.do","userName=mike&password=123",rollback,"json");
                    function rollback(param){
                        $.each(param,function (index,dmoObj){
                            alert(dmoObj.userName);
                            alert(dmoObj.password);
                        });
                        $("#div3").text("welcome");
                    }
                });
                $("#btn4").click(function(){
                    $.get("user/returnString2.do",rollback);
                    function rollback(param){
                        alert(param)
                        $("#div4").text(param);
                    }
                });
            });
        </script>
        <base href="<%=basePath%>">
    </head>
    <body>
        <h1>第一个springmvc项目</h1>
        <h2><a href="user/some.do" >发起some.do请求</a></h2>
        <h2>逐个接收参数
            <form action="user/other.do" method="post">
                用户名<input type="text" name="userName">
                用户密码<input type="text" name="password">
                <input type="submit" value="发起other.do请求">
            </form>
        </h2>
        <h2>对象接收参数
            <form action="user/otherUser.do" method="post">
                用户名<input type="text" name="userName">
                用户密码<input type="text" name="password">
                <input type="submit" value="发起otherUser.do请求">
            </form>
        </h2>
        <h2>
            <a href="user/returnString1.do">处理器方法返回String表示视图名称</a>
        </h2>
        <h2>
            <input type="button" id="btn1" value="void发起ajax请求">
            <div id="div1">123</div>
            <input type="button" id="btn2" value="User发起ajax请求">
            <div id="div2">123</div>
            <input type="button" id="btn3" value="List发起ajax请求">
            <div id="div3">123</div>
            <input type="button" id="btn4" value="String发起ajax请求">
            <div id="div4">123</div>
        </h2>
    <img src="static/jinGaoYin.JPG" />
    </body>
</html>
