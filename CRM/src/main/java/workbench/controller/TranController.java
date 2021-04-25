package workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import settings.entity.User;
import settings.service.UserService;
import utils.DateTimeUtil;
import utils.UUIDUtil;
import workbench.dao.ContactsDao;
import workbench.entity.Activity;
import workbench.entity.Contacts;
import workbench.entity.Tran;
import workbench.entity.TranHistory;
import workbench.service.ActivityService;
import workbench.service.ContactsService;
import workbench.service.CustomerService;
import workbench.service.TranService;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TranController {

    @Resource(name="userService")
    UserService userService;

    @Resource(name="activityService")
    ActivityService activityService;

    @Resource(name="contactsService")
    ContactsService contactsService;

    @Resource(name="customerService")
    CustomerService customerService;

    @Resource(name="tranService")
    TranService tranService;

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

    @RequestMapping("/workbench/transaction/getCustomerName")
    @ResponseBody
    public List<String> getCustomerName(String name){
        List<String> nameList=new ArrayList<>();
        nameList=customerService.getCustomerName(name);
        return nameList;
    }
    @RequestMapping(value = "/workbench/transaction/save",
                        method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest req){
        ModelAndView modelAndView=new ModelAndView();
        String id = UUIDUtil.getUUID();
        String owner = req.getParameter("owner");
        String money = req.getParameter("money");
        String name = req.getParameter("name");
        String expectedDate = req.getParameter("expectedDate");
        // 此处我们暂时只有客户名称,还没有id
        String customerName = req.getParameter("customerName");
        String stage = req.getParameter("stage");
        String type = req.getParameter("type");
        String source = req.getParameter("source");
        String activityId = req.getParameter("activityId");
        String contactsId = req.getParameter("contactsId");
        String createBy = ((User)req.getSession().getAttribute("user")).getName();
        String createTime = DateTimeUtil.getSysTime();
        String description = req.getParameter("description");
        String contactSummary = req.getParameter("contactSummary");
        String nextContactTime = req.getParameter("nextContactTime");

        Tran t = new Tran();
        t.setType(type);
        t.setName(name);
        t.setId(id);
        t.setDescription(description);
        t.setMoney(money);
        t.setExpectedDate(expectedDate);
        t.setStage(stage);
        t.setActivityId(activityId);
        t.setCreateTime(createTime);
        t.setCreateBy(createBy);
        t.setSource(source);
        t.setOwner(owner);
        t.setNextContactTime(nextContactTime);
        t.setContactSummary(contactSummary);
        t.setContactsId(contactsId);
        //要创建交易和交易历史，没有客户创建客户
        boolean flag=tranService.save(t,customerName);
        if(flag){
            modelAndView.setViewName("redirect:/workbench/transaction/index.jsp");
        }
        return modelAndView;
    }

    @RequestMapping("/workbench/transaction/detail")
    public ModelAndView detail(String id,HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        Tran tran=tranService.getById(id);
        ServletContext application =request.getServletContext();
        Map<String,String> pMap= (Map) application.getAttribute("pMap");
        String possibility=pMap.get(tran.getStage());
        modelAndView.addObject("possibility",possibility);
        modelAndView.addObject("tran",tran);
        modelAndView.setViewName("forward:/workbench/transaction/detail.jsp");
        return modelAndView;
    }

    @RequestMapping("/workbench/transaction/getHistoryList")
    @ResponseBody
    public List<TranHistory> getHistoryList(String tranId,
                                            HttpServletRequest request){
        List<TranHistory> list=new ArrayList<>();
        Map<String,String> pMap= (Map<String, String>)request.getServletContext().getAttribute("pMap");
        list=tranService.getHistoryList(tranId);
        for(TranHistory tranHistory:list){
            String possibility=pMap.get(tranHistory.getStage());
            tranHistory.setPossibility(possibility);
        }
        return list;
    }


}
