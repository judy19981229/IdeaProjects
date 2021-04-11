package ba02;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class myTest {
    @Test
    public  void test01(){
        String config="ba02/applicationContext.xml";
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext(config);
        Student myStudent= (Student) applicationContext.getBean("student");
        System.out.println(myStudent);
    }
    public  void test02(){}
    public  void test03(){}
    public  void test04(){}
    public  void test05(){}
}
