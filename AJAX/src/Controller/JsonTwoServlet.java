package Controller;

import workbench.entity.dept;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "JsonTwoServlet", value = "/Json_two")
public class JsonTwoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dept d1=new dept(10,"人事部","上海");
        dept d2=new dept(20,"研发部","北京");
        List list=new ArrayList();
        list.add(d1);
        list.add(d2);
        //使用json工具包 将dept对象转换为json格式字符串
        JSONObject jsonObj=JSONObject.fromObject(d1);
        JSONArray jsonArray=JSONArray.fromObject(list);
        System.out.println(jsonObj);
        System.out.println(jsonArray);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
