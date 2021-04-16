package workbench.controller;

import exception.SaveActivityException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import settings.entity.User;
import settings.service.UserService;
import utils.DateTimeUtil;
import utils.UUIDUtil;
import workbench.entity.Activity;
import workbench.service.ActivityService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class ActivityController {

    @Resource(name="userService")
    UserService userService;

    @Resource(name="activityService")
    ActivityService activityService;

    @RequestMapping("/workbench/activity/getUserList")
    @ResponseBody
    public List<User> getUserList(){
        List<User> list;
        list=userService.getUserList();
        return list;
    }

    @RequestMapping(value="/workbench/activity/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveActivity(Activity activity,
                                           HttpServletRequest request)
            throws SaveActivityException {
        String id=UUIDUtil.getUUID();//使用UUID工具类
        String createTime= DateTimeUtil.getSysTime();//使用DateTime工具类
        User user= (User) request.getSession().getAttribute("user");
        String createBy=user.getName();//从session中的user对象获取
        activity.setId(id);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);
        Map<String,Object> map=new HashMap<>();
        boolean result=activityService.saveActivity(activity);
        //程序正常走到这里说明没有抛出异常
        map.put("success",result);
        return map;
    }

}
