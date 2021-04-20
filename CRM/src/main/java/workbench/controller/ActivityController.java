package workbench.controller;

import exception.ActivityException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import settings.entity.User;
import settings.service.UserService;
import utils.DateTimeUtil;
import utils.MD5Util;
import utils.UUIDUtil;
import vo.PageVo;
import workbench.entity.Activity;
import workbench.entity.ActivityRemark;
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

    @RequestMapping("/workbench/activity/delete")
    @ResponseBody
    public Map<String,Object> delete(@RequestParam("id") String[] ids){
        //通过@RequestParam("参数名") String[]数组名 的形式接收从前端传过来的多条数据
        //这里的id写的是前端传递过来的参数的名称（id=xx1&id=xx2）
        Map<String,Object> map=new HashMap<>();
        boolean success=activityService.delete(ids);
        map.put("success",success);
        return map;
    }

    @RequestMapping("/workbench/activity/getActivity")
    @ResponseBody
    public Activity getActivity(String id){
        Activity activity=activityService.getActivity(id);
        return activity;
    }
    @RequestMapping(value="/workbench/activity/update",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateActivity(Activity activity,
                                             HttpServletRequest request) {

        activity.setEditTime(DateTimeUtil.getSysTime());
        User user= (User) request.getSession().getAttribute("user");
        activity.setEditBy(user.getName());
        Map<String,Object> map=new HashMap<>();
        boolean result=activityService.updateActivity(activity);
        map.put("success",result);
        return map;
    }
    @RequestMapping(value="/workbench/activity/detail")
    public ModelAndView detail(String id){
        Activity activity=activityService.detail(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("activity",activity);
        modelAndView.setViewName("forward:/workbench/activity/detail.jsp");
        return modelAndView;
    }
    @RequestMapping("/workbench/activity/getRemarkListById")
    @ResponseBody
    public List<ActivityRemark> getRemarkListById(String activityId){
        List<ActivityRemark> list=new ArrayList<>();
        list=activityService.getRemarkListById(activityId);
        return list;
    }

    @RequestMapping("/workbench/activity/saveRemark")
    @ResponseBody
    public Map<String,Object> saveRemark(String noteContent,String activityId,
                                         HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        ActivityRemark activityRemark=new ActivityRemark();
        User user= (User) request.getSession().getAttribute("user");

        activityRemark.setId(UUIDUtil.getUUID());
        activityRemark.setNoteContent(noteContent);
        activityRemark.setCreateBy(user.getName());
        activityRemark.setCreateTime(DateTimeUtil.getSysTime());
        activityRemark.setEditFlag("0");
        activityRemark.setActivityId(activityId);
        boolean flag=activityService.saveRemark(activityRemark);
        map.put("success",flag);
        return map;
    }
    @RequestMapping("/workbench/activity/deleteRemark")
    @ResponseBody
    public Map<String,Object> deleteRemark(String id){
        Map<String,Object> map=new HashMap<>();
        boolean flag=activityService.deleteRemark(id);
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/workbench/activity/editRemark")
    @ResponseBody
    public Map<String,Object> editRemark(String noteContent,String id,
                                         HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        ActivityRemark activityRemark=new ActivityRemark();
        User user= (User) request.getSession().getAttribute("user");
        activityRemark.setId(id);
        activityRemark.setNoteContent(noteContent);
        activityRemark.setEditBy(user.getName());
        activityRemark.setEditTime(DateTimeUtil.getSysTime());
        activityRemark.setEditFlag("1");
        boolean flag=activityService.editRemark(activityRemark);
        map.put("success",flag);
        return map;
    }

}
