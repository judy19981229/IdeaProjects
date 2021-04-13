import ba01.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class myTest {

    @Test
    public void Test02(){
        String config= "ba01/applicationContext.xml";
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext(config);
        Student myStudent= (Student) applicationContext.getBean("myStudentSet");
        System.out.println(myStudent.toString());
    }
    @Test
    public void Test03(){
        String config= "ba01/applicationContext.xml";
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext(config);
        Student myStudent= (Student) applicationContext.getBean("myStudentConstructor");
        System.out.println(myStudent.toString());
    }
    @Test
    public void Test04(){
        String config= "ba01/applicationContext.xml";
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext(config);
        Student myStudent1= (Student) applicationContext.getBean("myStudentByName");
        Student myStudent2= (Student) applicationContext.getBean("myStudentByType");
        System.out.println(myStudent1.toString());
        System.out.println(myStudent2.toString());
    }

}
