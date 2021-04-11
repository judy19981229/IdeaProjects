<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/4/4
  Time: 17:30
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
        <title>首页</title>
        <base href="<%=basePath%>">
        <%--<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>--%>
        <script src="static/jquery-3.6.0.min.js"></script>
        <script type="text/javascript">
            $(function(){
               $(":button").click(function(){
                    $.get("SelectStudent1",rollback,"json");
                    function  rollback(param){
                        $("#tbody").empty();
                        $.each(param,foreach);
                        function foreach(index,dmoObj){
                            $("#tbody").append("<tr>")
                            $("#tbody").append("<td>"+dmoObj.id+"</td>")
                            $("#tbody").append("<td>"+dmoObj.name+"</td>")
                            $("#tbody").append("<td>"+dmoObj.email+"</td>")
                            $("#tbody").append("<td>"+dmoObj.age+"</td>")
                            $("#tbody").append("</tr>")
                        }
                    }
               });
            });
        </script>
    </head>
    <body>
        <center>
            <a href="InsertStudent.jsp">注册学生</a><br/>
            <a href="SelectStudent">浏览学生(跳转页面)</a><br/>
            <input type="button" value="浏览学生(ajax)"><br/>
            <table border="2">
                <tr>
                    <td>id</td>
                    <td>name</td>
                    <td>email</td>
                    <td>age</td>
                </tr>
                <tbody id="tbody">

                </tbody>
            </table>
            <br/><br/><br/><br/><br/><br/><br/><br/>

            <form action="doSome">
                姓名<input type="text" name="name"/>
                年龄<input type="text" name="age"/>
                <input type="submit" value="doSome方法：forward">
            </form>

            <form action="doOther">
                姓名<input type="text" name="name"/>
                年龄<input type="text" name="age"/>
                <input type="submit" value="doOther方法：redirect">
            </form>

            <form action="doException">
                姓名<input type="text" name="name"/>
                年龄<input type="text" name="age"/>
                <input type="submit" value="处理异常">
            </form>

            <img src="static/jinGaoYin.JPG" />
        </center>
    </body>
</html>
