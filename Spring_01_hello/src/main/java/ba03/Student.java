package ba03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Component:用来创建对象的，等同于<bean>的功能，用在类的上面
//属性： value就是对象的名称，也就是bean的id值（表示对象的名称）
//value的值唯一的，创建的对象在整个spring容器中就只有一个
//相当于<bean id="myStudent" class="ba01.Student" />
//还需要告诉spring在哪里找到这个注解，要在配置文件中加入扫描器
//value可以省略
//@Component(value="myStudent")
//@Component("myStudent")
@Component
public class Student {
    //@value：给简单类型属性赋值
    //属性：value 是String类型的，表示简单类型的属性值
    //位置：1.在属性定义的上面，无需set方法，推荐使用 2.在set方法上面，用得少
    //value可以省略
    @Value("22")
    private Integer age;
    @Value("smith")
    private String name;

    //来自JDK中的注解，spring框架提供了对这个注解的功能支持，可以给引用类型赋值
    //使用的也是自动注入原理，支持byName，byType，默认是byName
    //先使用byName自动注入，如果byName赋值失败，再使用byType
    //如果想只使用byName方式，需要增加一个属性name
    //name的值是bean的id（名称）
    @Resource
    @Qualifier("mySchool")
    private School school;

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
