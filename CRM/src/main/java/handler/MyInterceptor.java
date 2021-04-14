package handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session= request.getSession();
        String name= (String) session.getAttribute("name");
        if(name==""||name==null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return false;
        }
        return true;
    }
}
