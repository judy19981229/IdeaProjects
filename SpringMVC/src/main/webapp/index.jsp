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
        <h1>?????????springmvc??????</h1>
        <h2><a href="user/some.do" >??????some.do??????</a></h2>
        <h2>??????????????????
            <form action="user/other.do" method="post">
                ?????????<input type="text" name="userName">
                ????????????<input type="text" name="password">
                <input type="submit" value="??????other.do??????">
            </form>
        </h2>
        <h2>??????????????????
            <form action="user/otherUser.do" method="post">
                ?????????<input type="text" name="userName">
                ????????????<input type="text" name="password">
                <input type="submit" value="??????otherUser.do??????">
            </form>
        </h2>
        <h2>
            <a href="user/returnString1.do">?????????????????????String??????????????????</a>
        </h2>
        <h2>
            <input type="button" id="btn1" value="void??????ajax??????">
            <div id="div1">123</div>
            <input type="button" id="btn2" value="User??????ajax??????">
            <div id="div2">123</div>
            <input type="button" id="btn3" value="List??????ajax??????">
            <div id="div3">123</div>
            <input type="button" id="btn4" value="String??????ajax??????">
            <div id="div4">123</div>
        </h2>
    <img src="static/jinGaoYin.JPG" />
    </body>
</html>
