package workbench.controller;

import workbench.dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "QuestionDeleteServlet",urlPatterns = "/delete_question")
public class QuestionDeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionId=request.getParameter("questionId");
        int result=0;
        QuestionDao dao=new QuestionDao();
        result=dao.delete(questionId);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out= response.getWriter();
        if(result==1){
            out.print("<font style='color:red;font-size:40'>试题删除成功</font>");
        }else{
            out.print("<font style='color:red;font-size:40'>试题删除失败</font>");
        }
    }
}
