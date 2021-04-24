package workbench.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import settings.entity.User;
import settings.service.UserService;
import utils.DateTimeUtil;
import utils.UUIDUtil;
import workbench.entity.Activity;
import workbench.entity.Clue;
import workbench.entity.Tran;
import workbench.service.ActivityService;
import workbench.service.ClueService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClueController {
    @Resource(name="userService")
    UserService userService;

    @Resource(name="activityService")
    ActivityService activityService;

    @Resource(name="clueService")
    ClueService clueService;

    @RequestMapping("/workbench/clue/getUserList")
    @ResponseBody
    public List<User> getUserList(){
        List<User> list;
        list=userService.getUserList();
        return list;
    }

    @RequestMapping(value = "/workbench/clue/save",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> save(Clue clue,
                                   HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        User user= (User) request.getSession().getAttribute("user");
        clue.setId(UUIDUtil.getUUID());
        clue.setCreateBy(user.getName());
        clue.setCreateTime(DateTimeUtil.getSysTime());
        boolean flag=clueService.save(clue);
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/workbench/clue/detail")
    @ResponseBody
    public ModelAndView detail(String id){
        ModelAndView modelAndView=new ModelAndView();
        Clue clue=new Clue();
        clue=clueService.detail(id);
        modelAndView.addObject("clue",clue);
        modelAndView.setViewName("forward:/workbench/clue/detail.jsp");
        return modelAndView;
    }

    @RequestMapping("/workbench/clue/getActivityListByClueId")
    @ResponseBody
    public List<Activity> getActivityListByClueId(String clueId){
        List<Activity> list;
        list=activityService.getActivityListByClueId(clueId);
        return list;
    }

    @RequestMapping("/workbench/clue/deleteClue")
    @ResponseBody
    public Map<String,Object> deleteClue(String id){
        Map<String,Object> map=new HashMap<>();
        boolean flag=clueService.deleteClue(id);
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/workbench/clue/getActivityListByName")
    @ResponseBody
    public List<Activity> getActivityListByName(String name,String clueId){
        List<Activity> list;
        list=activityService.getActivityListByName(name,clueId);
        return list;
    }

    @RequestMapping("/workbench/clue/bund")
    @ResponseBody
    public Map<String,Object> bund(String clueId,
                                   @RequestParam("activityId") String[] activityIds){
        Map<String,Object> map=new HashMap<>();
        boolean flag=clueService.bund(clueId,activityIds);
        map.put("success",flag);
        return map;
    }

    @RequestMapping("/workbench/clue/getActivityByName")
    @ResponseBody
    public List<Activity> getActivity(String activityName){
        List<Activity> list;
        list=activityService.getActivityByName(activityName);
        return list;
    }

    @RequestMapping(value="/workbench/clue/convert")
    @ResponseBody
    public ModelAndView convert(String clueId,String flag,
                                HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        Tran t=null;
        User user= (User) request.getSession().getAttribute("user");
        String createBy=user.getName();
        if("createTran".equals(flag)){//创建交易
            t=new Tran();
            String activityId= request.getParameter("activityId");
            String money=request.getParameter("money");
            String name=request.getParameter("name");
            String expectedDate=request.getParameter("expectedDate");
            String stage=request.getParameter("stage");
            String id=UUIDUtil.getUUID();
            String createTime=DateTimeUtil.getSysTime();

            t.setId(id);
            t.setActivityId(activityId);
            t.setMoney(money);
            t.setName(name);
            t.setExpectedDate(expectedDate);
            t.setStage(stage);
            t.setCreateBy(createBy);
            t.setCreateTime(createTime);
        }//这里的转换方法需要传三个参数：clueId,t,createBy(方便后面创建其它表)
        boolean result=clueService.convert(clueId,t,createBy);
        if (result) {//不需要携带参数，所以使用重定向(会更改地址栏)
            modelAndView.setViewName("redirect:/workbench/clue/index.jsp");
        }
        return modelAndView;
    }

}
