package Controller;

import dao.DeptDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeptDeleteServlet", value = "/dept_delete")
public class DeptDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String array[]=null;
        DeptDao dao=new DeptDao();
        int result=0;
        //1.读取浏览器发送的请求参数【若干empNo】
        array=request.getParameterValues("empNo");
        //2.dao删除
        result=dao.delete(array);
        //3.将删除结果写入到响应包中
        PrintWriter out= response.getWriter();
        out.print(result);
        //4.Tomcat将响应包推送给当前的异步请求对象
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
