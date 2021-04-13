package workbench.controller;

import workbench.entity.Questions;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ScoreServlet", urlPatterns = "/score")
public class ScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        List<Questions> list=new ArrayList();
        int score=0;
        response.setContentType("text/html;charset=utf-8");
        //1.从当前用户私人储物柜中得到系统提供的四道题目信息
        list=(List)session.getAttribute("exam");
        //2.从请求包中读取用户对于四道题给出的答案
        for(Questions question:list){
            Integer questionId=question.getQuestionId();
            String answer=question.getAnswer();
            String user_answer=request.getParameter("answer_"+questionId);
            //3.判分
            if(answer.equals(user_answer)){
                score+=25;
            }
        }
        //4.将分数写入到request，作为共享数据
        request.setAttribute("score",score);
        //5.请求转发调用jsp将用户本次分数写入到响应体
        request.getRequestDispatcher("score.jsp").forward(request,response);

    }
}
