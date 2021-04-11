package controller;

import dao.UserDao;
import entity.users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@javax.servlet.annotation.WebServlet(name = "UserAddServlet",urlPatterns = "/add_user")
public class UserAddServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用【请求对象】，读取【请求头】参数信息，得到用户的信息
        String userName,password,sex,email;
        UserDao dao=new UserDao();
        users user=null;
        int result=0;
        PrintWriter out=null;

        userName=request.getParameter("userName");
        password=request.getParameter("password");
        sex=request.getParameter("sex");
        email=request.getParameter("email");

        //2.调用【UserDao】，将用户信息填充到insert命令，借助Jdbc规范发送到数据库服务器
        user=new users(null,userName,password,sex,email);
        result=dao.add(user,request);
        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();

        //3.调用【响应对象】，将处理结果以二进制的形式写入到响应体中
        if(result==1){
            out.print("<font style='color:red;font-size:40'>" +
                    "<a href='/myWeb/login.html'>用户信息注册成功</a>" +
                    "</font>");
        }else{
            out.print("<font style='color:red;font-size:40'>" +
                    "<a href='/myWeb/user_add.html'>用户信息注册失败</a>" +
                    "</font>");
        }

        //Tomcat负责销毁【请求对象】和【响应对象】
        //Tomcat负责将Http响应协议包推送到发起请求的浏览器上
        //浏览器根据响应头Content-type指定编译器对响应体二进制内容编译
        //浏览器将编译后结果在窗口中展示给用户【结束】
    }
}
