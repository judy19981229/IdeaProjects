package controller;

import dao.QuestionDao;
import entity.Questions;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuestionFindServlet",urlPatterns = "/find_question")
public class QuestionFindServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDao dao=new QuestionDao();
        List<Questions> list=dao.find();
        request.setAttribute("question",list);
        request.getRequestDispatcher("/question_find.jsp").forward(request,response);
    }
}
