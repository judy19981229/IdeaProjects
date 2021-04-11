<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/3/11
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String result=(String) request.getAttribute("question_add");
%>
<font style="color: red;font-size: 50px">
    <%=result%>
</font>