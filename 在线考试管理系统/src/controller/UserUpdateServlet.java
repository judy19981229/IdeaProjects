package controller;

import dao.UserDao;
import entity.users;
import sun.rmi.server.Dispatcher;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserUpdateServlet",urlPatterns = "/update_user")
public class UserUpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId,userName,password,sex,email;
        users user=null;
        UserDao dao=new UserDao();
        PrintWriter out=null;
        int result=0;
        request.setCharacterEncoding("utf-8");

        userId=request.getParameter("userId");
        userName=request.getParameter("userName");
        password=request.getParameter("password");
        sex=request.getParameter("sex");
        email=request.getParameter("email");

        user= new users(Integer.valueOf(userId),userName,password,sex,email);
        result=dao.update(user);
        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();
        if(result==1){
            out.print("<font style='color:red;font-size:40'>用户信息更新成功</font>");
        } else{
            out.print("<font style='color:red;font-size:40'>用户信息更新失败</font>");
        }

    }
}
