<%@ page import="workbench.entity.users" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/3/20
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <center>
            <%
                List<users> userList=(List)request.getAttribute("user");
            %>
            <%--引入jquery的在线包--%>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script src="jquery-3.6.0.min.js"></script>
            <script type="text/javascript">
                window.onload=function(){
                    $(":checkbox:first").click(fun1);
                    function fun1(){
                        var flag=$(":checkbox:first").prop("checked");
                        $(":checkbox:gt(0)").prop("checked",flag);
                        var num=$(":checkbox:gt(0):checked").length;
                        if(num>0){
                            $(":button").prop("disabled",false);
                        } else{
                            $(":button").prop("disabled",true);
                        }
                    }
                    $(":checkbox:gt(0)").click(fun2);
                    function fun2(){
                        var numAll=$(":checkbox:gt(0)").length;
                        var num=$(":checkbox:gt(0):checked").length;
                        if(num==numAll){
                            $(":checkbox:first").prop("checked",true);
                        } else{
                            $(":checkbox:first").prop("checked",false);
                        }
                        if(num>0){
                            $(":button").prop("disabled",false);
                        } else{
                            $(":button").prop("disabled",true);
                        }
                    }
                    $(":button").click(fun3);
                    var param="";
                    function fun3(){
                        $(":checkbox:checked").each(checked)
                        $.get("/myWeb/delete_userChecked",param,callBack)
                    }
                    function checked(index,dmoObj){
                        if(param==""){
                            param="userId="+$(dmoObj).val();
                        } else{
                            param+="&userId="+$(dmoObj).val();
                        }
                    }
                    function callBack(param){
                        if(param>0){
                            $(":checkbox:checked").parent().parent().remove();
                        } else{
                            alert("服务器正在维护中")
                        }
                    }
                }
            </script>
            <table border="2">
                <tr>
                    <td>用户编号</td>
                    <td>用户姓名</td>
                    <td>用户密码</td>
                    <td>用户性别</td>
                    <td>用户邮箱</td>
                    <td>操作1</td>
                    <td>操作2</td>
                    <td><input type="checkbox" />全选</td>
                </tr>
                <%
                    for(users us:userList){
                %>
                <tr>
                    <td><%=us.getUserId()%></td>
                    <td><%=us.getUserName()%></td>
                    <td>******</td>
                    <td><%=us.getSex()%></td>
                    <td><%=us.getEmail()%></td>
                    <td>
                        <a href="/myWeb/delete_user?userId=<%=us.getUserId()%>">删除用户</a>
                    </td>
                    <td>
                        <a href="/myWeb/user_update.jsp?userId=<%=us.getUserId()%>&userName=<%=us.getUserName()%>&password=<%=us.getPassword()%>&sex=<%=us.getSex()%>&email=<%=us.getEmail()%>">更新用户信息</a>
                    </td>
                    <td><input type="checkbox" value="<%=us.getUserId()%>"/></td>
                </tr>
                <%
                    }
                %>
            </table>
            <table>
                <tr>
                    <td><input type="button" value="批量删除" disabled></td>
                </tr>
            </table>
        </center>

    </body>
</html>
