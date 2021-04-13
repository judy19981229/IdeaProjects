package workbench.controller;

import workbench.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UserCheckedServlet", value = "/delete_userChecked")
public class UserCheckedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int result=0;
        String[] array=null;
        array= request.getParameterValues("userId");
        UserDao dao=new UserDao();
        result=dao.deleteChecked(array);
        PrintWriter out= response.getWriter();
        out.print(result);
    }
}
