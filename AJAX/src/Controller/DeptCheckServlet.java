package Controller;

import workbench.dao.DeptDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeptFindServlet", value = "/dept_check")
public class DeptCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeptDao dao =new DeptDao();
        int result=0;
        PrintWriter out= response.getWriter();
        //1.读取请求协议包参数【部门名称】
        String deptName=request.getParameter("deptName");
        //2.Dao层查询这个部门名称是否已经存在
        result=dao.check(deptName);
        //3.将查询结果写入到【响应包】中
        out.print(result);
        //Tomcat将【响应包】推送给当前的异步请求对象
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
