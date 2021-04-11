package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TwoServlet",urlPatterns = "/two")
public class TwoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int jipai_money=10,hanbao_money=15,naicha_money=5,money=0,cost=0;
        String food=null,userName=null;
        Cookie cookieArray[]=null;
        Cookie newCard=null;
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out= response.getWriter();
        //1.读取请求头中的参数信息,得到用户点餐的食物类型
        food=request.getParameter("food");
        //2.读取请求中Cookie
        cookieArray=request.getCookies();
        //3.刷卡消费
        for(Cookie card:cookieArray){
            String key=card.getName();
            String value=card.getValue();
            if("userName".equals(key)){
                userName=value;
            }else if("money".equals(key)){
                money=Integer.valueOf(value);
                if("鸡排".equals(food)) {
                    if (money < jipai_money) {
                        out.print("用户" + userName + "余额不足请充值");
                    } else{
                        //card.setValue((money-jipai_money)+"");
                        newCard=new Cookie("money",(money-jipai_money)+"");
                        cost=jipai_money;
                    }
                }else if("汉堡".equals(food)) {
                    if (money < hanbao_money) {
                        out.print("用户" + userName + "余额不足请充值");
                    }else{
                        //card.setValue((money-hanbao_money)+"");
                        newCard=new Cookie("money",(money-hanbao_money)+"");
                        cost=hanbao_money;
                    }
                }else if("奶茶".equals(food)) {
                    if (money < naicha_money) {
                        out.print("用户" + userName + "余额不足请充值");
                    }else{
                        //card.setValue((money-naicha_money)+"");
                        newCard=new Cookie("money",(money-naicha_money)+"");
                        cost=naicha_money;
                    }
                }
            }
        }
        response.addCookie(newCard);
        out.print("用户"+userName+"本次消费"+cost+"元,用户余额"+newCard.getValue()+"");
    }
}
