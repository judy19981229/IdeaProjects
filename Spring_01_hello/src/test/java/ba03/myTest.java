package ba03;

import ba03.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class myTest {
    @Test
    public  void test01(){
        String config="ba03/applicationContext.xml";
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext(config);
        Student myStudent= (Student) applicationContext.getBean("student");
        System.out.println(myStudent);
    }
}
