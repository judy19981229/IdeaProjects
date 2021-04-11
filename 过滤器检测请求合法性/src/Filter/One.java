package Filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

//http://localhost:8080/myWeb/jinGaoYin.JPG?age=xx
public class One implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.通过拦截请求对象得到请求包参数信息，从而得到来访用户的真实年纪
        String age= servletRequest.getParameter("age");
        //2.根据年龄来帮助http服务器判断本次请求的合法性
        if(Integer.valueOf(age)>18){
            //将拦截请求对象和响应对象还给Tomcat，由Tomcat继续调用资源文件
            filterChain.doFilter(servletRequest,servletResponse);//放行
        } else{
            //过滤器代替Http服务器拒绝本次请求
            servletResponse.setContentType("text/html;charset=utf-8");
            PrintWriter out= servletResponse.getWriter();
            out.print("<font style='color:red;font-size:40px'>请求被拒绝，未成年人无法访问</font>");
        }
    }

}
