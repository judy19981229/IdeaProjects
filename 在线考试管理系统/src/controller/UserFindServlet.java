package controller;

import dao.UserDao;
import entity.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "UserFindServlet",urlPatterns="/find_user")
public class UserFindServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用【DAO】将查询命令推送到数据库服务器上，得到所有用户信息【list】
        UserDao dao=new UserDao();
        List<users> userList=dao.findAll();
        request.setAttribute("user",userList);
        //2.请求转发，通过jsp页面输出
        request.getRequestDispatcher("/user_find.jsp").forward(request,response);
    }
}

