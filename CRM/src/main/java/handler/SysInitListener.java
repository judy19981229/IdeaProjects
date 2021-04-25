package handler;

import org.springframework.web.context.support.WebApplicationContextUtils;
import settings.entity.DicValue;
import settings.service.DicService;
import settings.service.DicServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.*;

public class SysInitListener implements ServletContextListener {
    //该方法用来监听上下文域对象，服务器启动，上下文域对象创建，执行该方法
    //servletContextEvent能够取得监听的对象（上下文域对象）
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext application= servletContextEvent.getServletContext();
        //listener中不能使用注解，因为是在服务器启动的时候使用，需要用getBean方法
        DicService dicService=WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(DicServiceImpl.class);
        //按照code分类取出每一种数据字典，分别放到一个List集合中，把list集合放到一个map中
        Map<String, List<DicValue>> map=new HashMap<>();
        map=dicService.getAll(application);
        //将map解析为上下文域对象中保存的键值对
        Set<String> set= map.keySet();
        for(String key:set){
            application.setAttribute(key,map.get(key));
        }
        //处理Stage2Possibility.properties文件
        //将文件中的键值对关系处理成Java中的键值对关系,Map<String(阶段),String(可能性)> pMap
        //pMap.put("01资质审查",10)... application.setAttribute("pMap",pMap)
        Map<String,String> pMap=new HashMap<>();
        //解析properties文件
        ResourceBundle rb=ResourceBundle.getBundle("Stage2Possibility");
        Enumeration<String> e=rb.getKeys();
        while(e.hasMoreElements()){
            String key=e.nextElement();//阶段
            String value=rb.getString(key);//可能性
            pMap.put(key,value);
        }
        application.setAttribute("pMap",pMap);
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
