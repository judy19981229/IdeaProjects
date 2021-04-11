<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/3/11
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userId=request.getParameter("userId");
    String userName=request.getParameter("userName");
    String password=request.getParameter("password");
    String sex=request.getParameter("sex");
    String email=request.getParameter("email");
%>
<form action="/myWeb/update_user" method="get">
    <table>
        <tr>
            <td><input type="hidden" name="userId" value="<%=userId%>"/></td>
        </tr>
        <tr>
            <td>用户姓名</td>
            <td><input type="text" name="userName" value="<%=userName%>"/></td>
        </tr>
        <tr>
            <td>用户密码</td>
            <td><input type="text" name="password" value="<%=password%>"/></td>
        </tr>
        <tr>
            <td>用户性别</td>
            <td>
                <input type="radio" name="sex" value="男"
                        <% if("男".equals(sex)) { %> checked <% } %>/>男
                <input type="radio" name="sex" value="女"
                        <% if("女".equals(sex)) { %> checked <% } %>/>女
            </td>
        </tr>
        <tr>
            <td>用户邮箱</td>
            <td><input type="text" name="email" value="<%=email%>"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="更改"/></td>
            <td><input type="submit" value="复位"/><br></td>
        </tr>
    </table>
</form>