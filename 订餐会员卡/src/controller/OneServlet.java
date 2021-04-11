package controller;

import sun.rmi.server.Dispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "OneServlet",urlPatterns = "/one")
public class OneServlet extends javax.servlet.http.HttpServlet {
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.调用请求对象读取【请求头】参数信息
        String userName=request.getParameter("userName");
        String money=request.getParameter("money");
        //2.新建Cookie对象（开卡）
        Cookie card1=new Cookie("userName",userName);
        Cookie card2=new Cookie("money",money);
        //3.指定card在客户端硬盘上的生命周期（600s）
        card1.setMaxAge(60);
        card2.setMaxAge(60);
        //4.将Cookie写入到响应头交给浏览器（发卡）
        response.addCookie(card1);
        response.addCookie(card2);
        //5.通知Tomcat将【点餐页面】内容写入到响应体交给浏览器(请求转发）
        RequestDispatcher report=request.getRequestDispatcher("/index_2.html");
        report.forward(request,response);

    }
}
