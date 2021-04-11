package controller;

import entity.Student;
import exception.AgeException;
import exception.MyUserException;
import exception.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class StudentController {

    @Resource
    StudentService studentService;

    @RequestMapping(value = "/InsertStudent",produces = "text/plain;charset=utf-8")
    public ModelAndView InsertStudent(Student student){
        ModelAndView modelAndView=new ModelAndView();
        String tips;
        int result=studentService.insertStudent(student);
        if(result>0){
            tips=student.getName()+"注册成功";
        } else{
            tips=student.getName()+"注册失败";
        }
        modelAndView.addObject("tips",tips);
        modelAndView.setViewName("InsertResult");
        return  modelAndView;
    }

    @RequestMapping("/SelectStudent")
    public ModelAndView SelectStudent1(){
        ModelAndView modelAndView=new ModelAndView();
        List list = studentService.selectStudent();
        modelAndView.addObject("list",list);
        modelAndView.setViewName("SelectStudent");
        return  modelAndView;
    }
    @RequestMapping("/SelectStudent1")
    @ResponseBody
    public List<Student> SelectStudent2(){
        List list = studentService.selectStudent();
        return  list;
    }

    @RequestMapping("/doSome")
    public ModelAndView doSome(String name,Integer age){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("name",name);
        modelAndView.addObject("age",age);
        modelAndView.setViewName("forward:/showForward.jsp");
        return modelAndView;
    }

    @RequestMapping("/doOther")
    public ModelAndView doOther(String name,Integer age){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("name",name);
        modelAndView.addObject("age",age);
        modelAndView.setViewName("redirect:/showRedirect.jsp");
        //http://localhost:8080/myWeb/showRedirect.jsp?name=judy&age=18
        return modelAndView;
    }

    @RequestMapping("/doException")
    public ModelAndView doException(String name , Integer age) throws MyUserException {
        ModelAndView modelAndView=new ModelAndView();
        if(!"judy".equals(name)){
            throw new NameException("用户名不正确，请重试");
        }
        if(age<18||age>80){
            throw new AgeException("年龄不符合要求，请重试");
        }
        modelAndView.setViewName("show");
        return modelAndView;
    }

}
