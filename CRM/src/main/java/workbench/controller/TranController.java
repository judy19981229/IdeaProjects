package workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import settings.entity.User;
import settings.service.UserService;
import workbench.dao.ContactsDao;
import workbench.entity.Activity;
import workbench.entity.Contacts;
import workbench.service.ActivityService;
import workbench.service.ContactsService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TranController {

    @Resource(name="userService")
    UserService userService;

    @Resource(name="activityService")
    ActivityService activityService;

    @Resource(name="contactsService")
    ContactsService contactsService;

    @RequestMapping("/workbench/transaction/add")
    public ModelAndView getUserList(){
        ModelAndView modelAndView=new ModelAndView();
        List<User> userList;
        userList=userService.getUserList();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("forward:/workbench/transaction/save.jsp");
        return modelAndView;
    }
    @RequestMapping("/workbench/transaction/getActivity")
    @ResponseBody
    public List<Activity>  getActivityByName(String activityName){
        List<Activity> aList;
        aList=activityService.getActivityByName(activityName);
        return aList;
    }
    @RequestMapping("/workbench/transaction/getContacts")
    @ResponseBody
    public List<Contacts> getContacts(String contactsName){
        List<Contacts> cList;
        cList=contactsService.getContacts(contactsName);
        return cList;
    }
}
