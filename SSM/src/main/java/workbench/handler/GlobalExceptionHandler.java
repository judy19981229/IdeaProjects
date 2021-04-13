package workbench.handler;

import exception.AgeException;
import exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ControllerAdvice注解：
 * 控制器增强（给控制器增加功能-异常处理功能）
 * 必须让框架知道这个注解所在的包名，需要在springmvc配置文件中声明组件扫描器，指定handler包
* */
@ControllerAdvice
public class GlobalExceptionHandler {
    /** 定义方法，处理发生的异常
     处理异常的方法和控制器方法的定义一样，可以有多个参数，可以有ModelAndView，String，对象类型等返回值
     形参：Exception exception 表示controller中抛出的异常对象，获取发生的异常信息
     @ExceptionHandler注解：
     表示异常的类型，当发生此类型异常时，由当前方法处理* */
    @ExceptionHandler(NameException.class)
    public ModelAndView NameException(Exception exception){
        /**异常发生处理逻辑：
         1.记录异常（发生时间，哪里发生，异常内容）到数据库和日志文件
         2.发送通知，把异常的信息通过邮件、短信、微信等发送给相关人员
         3.给用户友好提示**/
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("message","姓名必须是judy");
        modelAndView.addObject("Exception",exception);
        modelAndView.setViewName("NameError");
        return modelAndView;
    }
    @ExceptionHandler(AgeException.class)
    public ModelAndView AgeException(Exception exception){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("message","年龄必须在18-80之间");
        modelAndView.addObject("Exception",exception);
        modelAndView.setViewName("AgeError");
        return modelAndView;
    }
    //处理其他异常，NameException和AgeException意外事件的异常
    //相当于if{}else{}中的else，只能有一个
    @ExceptionHandler
    public ModelAndView Exception(Exception exception){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("message","出现了其他异常");
        modelAndView.addObject("Exception",exception);
        modelAndView.setViewName("ElseError");
        return modelAndView;
    }
}
