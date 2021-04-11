package service;

import ba03.Student;
import org.springframework.stereotype.Component;
import service.SomeService;

@Component()
public class ServiceImpl implements SomeService {
    @Override
    public Student doSome(String name,Integer age) {
        System.out.println("执行了doSome方法");
        Student student=new Student(age,name,null);
        return student;
    }

    @Override
    public Student doOther(String name, Integer age) {
        Student student=new Student(age,name,null);
        System.out.println("执行了doOther方法");
        return student;
    }

}
