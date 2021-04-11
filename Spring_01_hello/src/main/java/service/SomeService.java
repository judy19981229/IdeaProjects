package service;

import ba03.Student;
import org.springframework.stereotype.Component;


public interface SomeService {
    public Student doSome(String name, Integer age);
    public Student doOther(String name, Integer age);
}
