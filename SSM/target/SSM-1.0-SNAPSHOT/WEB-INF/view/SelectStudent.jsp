<%@ page import="java.util.List" %>
<%@ page import="entity.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/4/6
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
    List<Student> studentList = (List<Student>) request.getAttribute("list");
%>

<html>
    <head>
        <title>浏览学生</title>
        <base href="<%=basePath%>">
    </head>
    <body>
        <center>
            <table border="2">
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>email</th>
                    <th>age</th>
                </tr>
                <%
                    for(Student student:studentList){
                %>
                        <tr>
                            <td><%=student.getId()%></td>
                            <td><%=student.getName()%></td>
                            <td><%=student.getEmail()%></td>
                            <td><%=student.getAge()%></td>
                        </tr>
                <%
                    }
                %>
            </table>
        </center>
    </body>
</html>
