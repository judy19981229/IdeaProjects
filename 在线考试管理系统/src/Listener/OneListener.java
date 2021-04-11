package Listener;

import util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OneListener implements ServletContextListener {
    //在Tomcat启动时，预先创建20个Connection，在userDao.add方法执行时
    //将事先创建好的Connection交给add方法
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        JdbcUtil util=new JdbcUtil();
        Map map=new HashMap();
        for(int i=0;i<20;i++){
            Connection con= util.getCon();
            System.out.println("在http服务器启动时，创建Connection" +con);
            map.put(con,true); //true表示这个通道处于空闲状态，false表示通道正在被使用
        }
        //为了在http服务器运行期间，一直都可以使用这20个Connection
        // 将Connection保存到全局作用域对象
        ServletContext application=servletContextEvent.getServletContext();
        application.setAttribute("key1",map);
    }
    //map被销毁
    //在http服务器关闭时，将全剧作用域对象20个Connection销毁
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext application=servletContextEvent.getServletContext();
        Map map=(Map)application.getAttribute("key1");
        Iterator it=map.keySet().iterator();
        while(it.hasNext()){
            Connection con=(Connection) it.next();
            if(con!=null){
                System.out.println("销毁"+con);
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
