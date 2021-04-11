import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TwoServlet",urlPatterns="/two",loadOnStartup=9)
// 为servlet接口实现类提供短暂别名
// 通知Tomcat在启动时负责创建TwoServlet实例对象
public class TwoServlet extends HttpServlet {
    public TwoServlet() {
        System.out.println("TwoServlet实例对象被创建了");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TwoServlet的doPost方法");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("TwoServlet的doGet方法");
    }
}
