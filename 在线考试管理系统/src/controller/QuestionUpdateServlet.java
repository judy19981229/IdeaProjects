package controller;

import dao.QuestionDao;
import entity.Questions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "QuestionUpdateOneServlet",urlPatterns = "/update_question")
public class QuestionUpdateServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        PrintWriter out=null;
        int result=0;
        String questionId,title,optionA,optionB,optionC,optionD,answer;
        Questions question;
        QuestionDao dao= new QuestionDao();

        questionId=request.getParameter("questionId");
        title=request.getParameter("title");
        optionA=request.getParameter("optionA");
        optionB=request.getParameter("optionB");
        optionC=request.getParameter("optionC");
        optionD=request.getParameter("optionD");
        answer=request.getParameter("answer");
        question=new Questions(Integer.valueOf(questionId),title,optionA,optionB,optionC,optionD,answer);

        result=dao.update(question);
        response.setContentType("text/html;charset=utf-8");
        out= response.getWriter();
        if(result==1){
            out.print("<font style='color:red;font-size:40'>试题信息更新成功</font>");
        } else{
            out.print("<font style='color:red;font-size:40'>试题信息更新失败</font>");
        }
    }
}
