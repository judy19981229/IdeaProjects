package controller;

import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet", value = "/UserLogin")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");
        String sex=request.getParameter("sex");
        String email=request.getParameter("email");
        User user=new User(null,userName,password,sex,email);
        //使用监听器来获取容器对象
        ServletContext sc=getServletContext();
        WebApplicationContext webApplicationContext=WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        UserService userService= (UserService) webApplicationContext.getBean("userServiceImpl");
        int result=userService.insertUser(user);
        if(result>0){
            request.getRequestDispatcher("/result.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
