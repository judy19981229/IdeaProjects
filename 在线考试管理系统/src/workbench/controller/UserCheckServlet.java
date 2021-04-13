package workbench.controller;

import workbench.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserCheckServlet", value = "/check_user")
public class UserCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=null;
        int check=0;
        UserDao dao=new UserDao();
        userName=request.getParameter("userName");
        check=dao.check(userName);
        PrintWriter out= response.getWriter();
        out.print(check);
    }

}
