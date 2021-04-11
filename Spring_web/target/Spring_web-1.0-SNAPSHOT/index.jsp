<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/4/1
  Time: 09:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>用户注册</title>
    </head>
    <body>
        <form action="UserLogin" method="get">
            <table>
                <tr>
                    <td>用户姓名</td>
                    <td><input type="text" name="userName"></td>
                </tr>
                <tr>
                    <td>用户密码</td>
                    <td><input type="text" name="password"></td>
                </tr>
                <tr>
                    <td>用户性别</td>
                    <td>
                        <input type="radio" name="sex" value="男">男
                        <input type="radio" name="sex" value="女">女
                    </td>
                </tr>
                <tr>
                    <td>用户邮箱</td>
                    <td><input type="text" name="email"></td>
                </tr>
                <tr>
                    <th><input type="submit" value="用户注册"></th>
                    <th><input type="reset" value="复位"></th>
                </tr>
            </table>
        </form>

    </body>
</html>
