import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "OneServlet",urlPatterns = "/one")
public class OneServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用请求对象，读取请求头参数信息，得到用户选择商品名
        String goodsName;
        goodsName=request.getParameter("goodsName");
        //2.调用请求对象，向Tomcat索要用户在服务端的私人储物柜
        HttpSession session= request.getSession();
        //3.将用户选购商品添加到用户的私人储物柜中
        Integer goodsNumber=(Integer)session.getAttribute(goodsName);
        if(goodsNumber==null){
            session.setAttribute(goodsName,1);
        }else{
            session.setAttribute(goodsName,goodsNumber+1);
        }
    }
}
