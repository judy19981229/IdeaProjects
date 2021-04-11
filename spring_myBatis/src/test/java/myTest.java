import dao.StudentDao;
import entity.Star;
import entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.StarService;
import service.StudentService;
import vo.StarAndClass;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class myTest {
    String config="applicationContext.xml";
    ApplicationContext applicationContext=new ClassPathXmlApplicationContext(config);
    StudentService studentService= (StudentService) applicationContext.getBean("studentServiceImpl");
    StarService starService= (StarService) applicationContext.getBean("starServiceImpl");
    @Test
    public void test1(){
        StudentDao studentDao= (StudentDao) applicationContext.getBean("studentDao");
        List<Student> studentList=studentDao.selectStudents();
        for(Student student:studentList){
            System.out.println(student);
        }
    }
    @Test
    public void test2(){
        List<Student> studentList=studentService.selectStudent();
        for(Student student:studentList){
            System.out.println(student);
        }
    }
    @Test
    public void test3(){
        List<Star> list=starService.selectStar();
        for(Star star:list){
            System.out.println(star);
        }
    }
    @Test
    public void test4(){
        List<Map<String,Object>> mapList=starService.selectClass();
        for(Map<String,Object> map:mapList){
            Set<String> set= map.keySet();
            for(String key:set){
                System.out.println("key="+key);
                System.out.println("value="+map.get(key));
                System.out.println("-----------");
            }
        }
    }
    @Test
    public void test5(){
        List<StarAndClass> list=starService.selectStarAndClass("z");
        for(StarAndClass star:list){
            System.out.println(star);
        }
    }
}

