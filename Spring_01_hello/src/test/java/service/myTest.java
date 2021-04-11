package service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class myTest {
    String config= "service/applicationContext.xml";
    ApplicationContext applicationContext=new ClassPathXmlApplicationContext(config);
    SomeService proxy= (SomeService) applicationContext.getBean("serviceImpl");
    @Test
    public void test1(){
        proxy.doSome("mike",18);

    }
    @Test
    public void test2(){
        proxy.doOther("alien",24);
    }

}
