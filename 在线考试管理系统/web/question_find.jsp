<%@ page import="java.util.List" %>
<%@ page import="workbench.settings.entity.Questions" %>
<%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/3/12
  Time: 08:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Questions> list=(List)request.getAttribute("question");
%>
<center>
    <table border="2">
        <tr>
            <th>题目编号</th>
            <th>题目标题</th>
            <th>选项A</th>
            <th>选项B</th>
            <th>选项C</th>
            <th>选项D</th>
            <th>正确答案</th>
            <th>操作1</th>
            <th>操作2</th>
        </tr>
        <%
            for(Questions question:list){
        %>
        <tr>
            <td><%=question.getQuestionId()%></td>
            <td><%=question.getTitle()%></td>
            <td><%=question.getOptionA()%></td>
            <td><%=question.getOptionB()%></td>
            <td><%=question.getOptionC()%></td>
            <td><%=question.getOptionD()%></td>
            <td><%=question.getAnswer()%></td>
            <td><a href="/myWeb/delete_question?questionId=<%=question.getQuestionId()%>">删除试题</a></td>
            <td>
                <a href="/myWeb/question_update.jsp?questionId=<%=question.getQuestionId()%>&title=<%=question.getTitle()%>&optionA=<%=question.getOptionA()%>&optionB=<%=question.getOptionB()%>&optionC=<%=question.getOptionC()%>&optionD=<%=question.getOptionD()%>&answer=<%=question.getAnswer()%>">
                    更新试题</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</center>

