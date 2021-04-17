package workbench.controller;

import exception.ActivityException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import settings.entity.User;
import settings.service.UserService;
import utils.DateTimeUtil;
import utils.UUIDUtil;
import vo.PageVo;
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
            throws ActivityException {
        String id=UUIDUtil.getUUID();//使用UUID工具类
        String createTime= DateTimeUtil.getSysTime();//使用DateTime工具类
        User user= (User) request.getSession().getAttribute("user");
        String createBy=user.getName();//从session中的user对象获取
        activity.setId(id);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);
        Map<String,Object> map=new HashMap<>();
        boolean result= false;
        result = activityService.saveActivity(activity);
        //程序正常走到这里说明没有抛出异常
        map.put("success",result);
        return map;
    }

    @RequestMapping("/workbench/activity/pageList")
    @ResponseBody
    public PageVo<Activity> pageList(String name,String owner,String startDate,
                           String endDate,Integer pageNo,Integer pageSize){

        PageVo<Activity> pageVo=new PageVo<Activity>();
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        //使用分页插件进行分页操作（传入的是需要展示的页数，和每页展示的条数）
        //如果使用mysql中的limit方法进行分页，就需要传入略过的记录数和每页展示的条数
        //略过的记录数：skipCount=（pageNo-1）*pageSize
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);
        pageVo=activityService.pageList(map);
        return pageVo;
    }

}
