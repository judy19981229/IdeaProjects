package workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import settings.entity.User;
import settings.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ActivityController {

    @Resource(name="userService")
    UserService userService;

    @RequestMapping("/workbench/activity/getUserList")
    @ResponseBody
    public List<User> getUserList(){
        List<User> list=new ArrayList<>();
        list=userService.getUserList();
        return list;
    }
}
