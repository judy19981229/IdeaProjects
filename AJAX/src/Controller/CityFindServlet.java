package Controller;

import dao.cityDao;
import entity.city;
import net.sf.json.JSON;
import net.sf.json.JSONArray;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CityFindServlet", value = "/city_find")
public class CityFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cityDao dao=new cityDao();
        List<city> list;
        String provinceId;
        JSONArray jsonArray;
        //1.读取请求协议包参数【省份编号】
        provinceId= request.getParameter("provinceId");
        //2.dao层查询隶属于当前省份下的所有城市集合
        list=dao.find(provinceId);
        //3.JSON工具包转换为【JSON格式字符串】
        jsonArray=JSONArray.fromObject(list);
        //4.将【JSON格式字符串】写入到响应体中
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonArray);
        //Tomcat负责将【响应包】推送给当前的异步请求对象
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
