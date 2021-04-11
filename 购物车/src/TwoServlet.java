import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "TwoServlet",urlPatterns = "/two")
public class TwoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用请求对象，向Tomcat索要当前用户在服务端私人储物柜
        HttpSession session=request.getSession();
        request.setCharacterEncoding("utf-8");
        //2.将session中所有的key读取出来，存放一个枚举对象
        Enumeration goodsNames=session.getAttributeNames();
        String goodsName;
        int goodsNumber;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out= response.getWriter();
        while(goodsNames.hasMoreElements()){
            goodsName=(String) goodsNames.nextElement();
            goodsNumber=(int)session.getAttribute(goodsName);
            out.print("商品名称"+goodsName+" 商品数量"+goodsNumber+"");
            out.print("<br/>");
        }

    }
}
