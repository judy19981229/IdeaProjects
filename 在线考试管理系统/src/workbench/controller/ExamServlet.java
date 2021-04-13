package workbench.controller;

import workbench.dao.QuestionDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ExamServlet",urlPatterns = "/exam")
public class ExamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao dao=new QuestionDao();
        List list=new ArrayList();
        HttpSession session= request.getSession();
        //1.调用Dao对象，随机从question中拿出四道题目
        list=dao.exam();
        //2.将这四道题目添加到session中作为共享数据
        //request.setAttribute("exam",list);
        session.setAttribute("exam",list);
        //3.通过请求转发，申请调用exam.jsp将四道题目输出到响应体中
        request.getRequestDispatcher("exam.jsp").forward(request,response);
    }

}
