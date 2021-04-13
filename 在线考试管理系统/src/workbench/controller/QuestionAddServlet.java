package workbench.controller;

import workbench.dao.QuestionDao;
import workbench.entity.Questions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "QuestionAddServlet" ,urlPatterns = "/add_question")
public class QuestionAddServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用请求对象读取请求头信息，得到新增信息内容
        request.setCharacterEncoding("utf-8");
        String title,optionA,optionB,optionC,optionD,answer;
        Questions question;
        QuestionDao dao=new QuestionDao();
        int result;
        title=request.getParameter("title");
        optionA=request.getParameter("optionA");
        optionB=request.getParameter("optionB");
        optionC=request.getParameter("optionC");
        optionD=request.getParameter("optionD");
        answer=request.getParameter("answer");
        question= new Questions(null,title,optionA,optionB,optionC,optionD,answer);
        //2.调用Dao对象将insert命令推送到数据库，并得到处理结果
        result=dao.add(question);
        //3.通过请求转发，向Tomcat索要question_add.jsp文件，将处理结果写入到响应体中
        if(result==1){
            request.setAttribute("question_add","试题添加成功");
        }else{
            request.setAttribute("question_add","试题添加失败");
        }
        request.getRequestDispatcher("/question_add.jsp").forward(request,response);
    }
}
