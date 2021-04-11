<%@ page import="java.util.List" %>
<%@ page import="com.wh.Student" %><%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/3/11
  Time: 09:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Student> stuList=(List)request.getAttribute("key");
%>
<table>
  <tr>
    <td>用户编号</td>
    <td>用户姓名</td>
  </tr>
  <%
    for(Student stu:stuList){
  %>
  <tr>
    <td><%=stu.getSid()%></td>
    <td><%=stu.getSname()%></td>
  </tr>
  <%
    }
  %>
</table>
