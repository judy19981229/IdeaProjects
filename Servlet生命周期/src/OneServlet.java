import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "OneServlet",urlPatterns="/one")
//为servlet接口实现类提供短暂别名
//OneServlet会在第一个用户访问时创建实例对象
public class OneServlet extends javax.servlet.http.HttpServlet {
    public OneServlet() {
        System.out.println("OneServlet类被创建实例对象");
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("OneServlet的doPost方法");
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        System.out.println("OneServlet的doGet方法");
    }
}
