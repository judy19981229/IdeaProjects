package ba01;

import org.springframework.stereotype.Component;

public class Student {
    private Integer age;
    private  String name;
    private  School school;

    public Student() {
    }

    public Student(Integer age, String name, School school) {
        this.age = age;
        this.name = name;
        this.school = school;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", school=" + school +
                '}';
    }
}
