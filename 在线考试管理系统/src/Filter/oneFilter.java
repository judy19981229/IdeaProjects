package Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class oneFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //拦截后，通过请求对象向Tomcat索要当前用户的HttpSession
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpSession session= request.getSession(false);
        //1.调用请求对象读取请求包中请求行中的URI，了解用户访问的资源文件是谁
        String uri=request.getRequestURI();//[/网站名/资源文件名 /myWeb/login.html or ...
        //2.如果本次请求资源文件与登录相关【login.html /LoginServlet】此时应该无条件放行
        if(uri.indexOf("login")!=-1 || uri.indexOf("add")!=-1 ||"/myWeb/".equals(uri) || session!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        } else{
            request.getRequestDispatcher("/login_error.html").forward(servletRequest,servletResponse);
        }

        /*之前的拦截方法
        HttpSession session=request.getSession(false);
        if(session==null){
            request.getRequestDispatcher("/login_error.html").forward(servletRequest,servletResponse);
        } else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
         */
    }

    @Override
    public void destroy() {

    }
}
