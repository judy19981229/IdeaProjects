package handler;

import settings.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        //向下转型
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        //获取的是http://127.0.0.1:8080/CRM之后的路径
        String path=request.getServletPath();
        // System.out.println(path); 正常登录的话获得的是/login.jsp
        //登陆页面不拦截,这里一定要加前面的/，不然无法登录
        if("/login.jsp".equals(path)){
            filterChain.doFilter(request,response);
        } else{
            User user= (User) request.getSession().getAttribute("user");
            if(user!=null){
                filterChain.doFilter(request,response);
            } else{
                //请求转发最好不要写死/CRM/login.jsp，方便以后更改
                response.sendRedirect(request.getContextPath()+"/login.jsp");
                //System.out.println(request.getContextPath()); 获得的是/CRM
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }
}
