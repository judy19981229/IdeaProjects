<%@ page import="java.util.List" %>
<%@ page import="entity.Questions" %><%--
  Created by IntelliJ IDEA.
  User: judy
  Date: 2021/3/13
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //List<Questions> list=(List)request.getAttribute("exam");
    List<Questions> list=(List)session.getAttribute("exam");

%>
    <center>
        <form action="/myWeb/score">
            <table border="2">
                <tr>
                    <td>试题编号</td>
                    <td>试题信息</td>
                    <td>选项：A</td>
                    <td>选项：B</td>
                    <td>选项：C</td>
                    <td>选项：D</td>
                    <td>答案</td>
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
                        <td>
                            <input type="radio" name="answer_<%=question.getQuestionId()%>" value="A" />A
                            <input type="radio" name="answer_<%=question.getQuestionId()%>" value="B" />B
                            <input type="radio" name="answer_<%=question.getQuestionId()%>" value="C" />C
                            <input type="radio" name="answer_<%=question.getQuestionId()%>" value="D" />D
                        </td>
                    </tr>
                <%
                    }
                %>
                <tr>
                    <th colspan="3">
                        <input type="submit" value="交卷"/>
                    </th>
                    <th colspan="4">
                        <input type="reset" value="重做"/>
                    </th>
                </tr>
            </table>
        </form>
    </center>
