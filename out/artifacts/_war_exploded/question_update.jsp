<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/3/12
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String questionId,title,optionA,optionB,optionC,optionD,answer;
    questionId=request.getParameter("questionId");
    title=request.getParameter("title");
    optionA=request.getParameter("optionA");
    optionB=request.getParameter("optionB");
    optionC=request.getParameter("optionC");
    optionD=request.getParameter("optionD");
    answer=request.getParameter("answer");
%>
<center>
    <form action="/myWeb/update_question">
        <table>
            <tr>
                <td>试题编号</td>
                <td><input type="text" name="questionId" value="<%=questionId%>" readonly/></td>
            </tr>
            <tr>
                <td>题目：</td>
                <td><input type="text" name="title" value="<%=title%>"/></td>
            </tr>
            <tr>
                <td>选项A：</td>
                <td><input type="text" name="optionA" value="<%=optionA%>"/></td>
            </tr>
            <tr>
                <td>选项B：</td>
                <td><input type="text" name="optionB" value="<%=optionB%>"/></td>
            </tr>
            <tr>
                <td>选项C：</td>
                <td><input type="text" name="optionC" value="<%=optionC%>"/></td>
            </tr>
            <tr>
                <td>选项D：</td>
                <td><input type="text" name="optionD" value="<%=optionD%>"/></td>
            </tr>
            <tr>
                <td>正确答案：</td>
                <td>
                    <input type="radio" name="answer" value="A"
                            <%if("A".equals(answer)){%> checked <%}%>/>A
                    <input type="radio" name="answer" value="B"
                            <%if("B".equals(answer)){%> checked <%}%>/>B
                    <input type="radio" name="answer" value="C"
                            <%if("C".equals(answer)){%> checked <%}%>/>C
                    <input type="radio" name="answer" value="D"
                            <%if("D".equals(answer)){%> checked <%}%>/>D
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="更新试题"/></td>
                <td><input type="reset" value="复位"/></td>
            </tr>
        </table>
    </form>
</center>
