package workbench.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import workbench.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//创建处理器对象，对象放在springmvc容器中（处理请求的都是控制器/处理器）
@Controller
@RequestMapping("/user")
public class MyController {
    //处理用户提交的请求,springmvc中是使用方法来处理的，
    //方法是自定义的，可以有多种返回值，多种参数，方法名称自定义

    //准备使用doSome方法来处理some.do请求
    //@RequestMapping:请求映射,作用是把一个请求地址和一个方法绑定在一起,一个请求绑定一个方法处理
    //属性：1.value是一个String，表示请求的uri地址的(/some.do),value值必须是唯一的,在使用时推荐地址以/为开头
    //位置：在方法上面（最常用），或者在类的上面
    //使用@RequestMapping修饰的方法叫做处理器方法，可以处理请求的，类似servlet中的doGet和doPost
    //返回值：ModelAndView Model(数据,请求处理完成后显示给用户看的) View(视图,jsp)
    @RequestMapping(value="/some.do",method= RequestMethod.GET)
    public ModelAndView doSome(){ //doGet()----service请求处理
        //处理some.do请求，相当于service调用处理完成了
        ModelAndView mv=new ModelAndView();
        //添加数据，框架在请求的最后把数据放到request作用域
        //request.setAttribute("message","欢迎使用springmvc");
        mv.addObject("message","欢迎使用springmvc");
        mv.addObject("fun","执行的是doSome方法");
        //指定视图
        //框架对视图执行的是forward操作，request.getRequestDispather(/show.jsp).forward(req,res);
        mv.setViewName("show");
        //返回mv
        return mv;
        //把数据的处理结果放到mv里面，把需要跳转的页面放到mv里面
    }

    @RequestMapping(value="/other.do",method= RequestMethod.POST)
    public ModelAndView doOther(HttpServletRequest request,
                                String password){
        ModelAndView mv=new ModelAndView();
        mv.addObject("message","欢迎使用springmvc");
        mv.addObject("fun","执行的是doOther方法");
        mv.addObject("userName",request.getParameter("userName"));
        mv.addObject("password",password);
        mv.setViewName("show_post");
        return mv;
    }
    @RequestMapping(value="/otherUser.do",method= RequestMethod.POST)
    public ModelAndView doOtherUser(User user){
        ModelAndView mv=new ModelAndView();
        mv.addObject("message","欢迎使用springmvc");
        mv.addObject("fun","执行的是doOtherUser方法");
        mv.addObject("userName",user.getUserName());
        mv.addObject("password",user.getPassword());
        mv.addObject("user",user);
        mv.setViewName("show_post_user");
        return mv;
    }
    //处理器方法返回String-表示逻辑视图名称，需要配置视图解析器
    @RequestMapping(value="/returnString1.do")
    public String doReturnString1(){
        return "show";
    }
    //返回void
    @RequestMapping(value="/returnVoid.do")
    public void doReturnVoid(HttpServletResponse response,String userName,String password) throws IOException {
        User user=new User(userName,password);
        //把结果对象转换成json的数据格式
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(user);
        System.out.println(json);
        //输出数据，响应ajax的请求
        response.getWriter().print(json);
    }
    //返回Object
    @ResponseBody
    @RequestMapping("/returnUser.do")
    public User doReturnObject(User user){
        return user;
    }

    //返回一个List集合
    @RequestMapping("/returnList.do")
    @ResponseBody
    public List<User> doReturnList(User user){
        List<User> list=new ArrayList<>();
        list.add(user);
        User user1=new User("alien","123");
        list.add(user1);
        return list;
    }
    //返回String,只有写了ResponseBody注释之后，返回的才是String，不然是视图view
    @RequestMapping(value = "/returnString2.do",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String doReturnString2(){
        //中文会导致返回到浏览器的值为乱码，需要设置编码方式为utf-8
        return "你好，欢迎光临";
    }
}
