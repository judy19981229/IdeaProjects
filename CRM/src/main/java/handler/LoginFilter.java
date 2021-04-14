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
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        //获取的是http://127.0.0.1:8080/CRM/之后的路径
        String path=request.getServletPath();
        //登陆页面和登录操作不拦截
        if("/login.jsp".equals(path) || "/settings/user/login".equals(path)){
            filterChain.doFilter(request,response);
        } else{
            User user= (User) request.getSession().getAttribute("user");
            if(user!=null){
                filterChain.doFilter(request,response);
            } else{
                response.sendRedirect(request.getContextPath()+"/login.jsp");
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
