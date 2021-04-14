package settings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import settings.entity.User;
import settings.service.UserService;
import utils.MD5Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/settings/user/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(String loginAct, String loginPwd,
                                    HttpServletRequest request){
        Map<String,Object> map=new HashMap<String,Object>();
        String ip=request.getLocalAddr();
        User us=new User();
        us.setAllowIps(ip);
        us.setLoginAct(loginAct);
        us.setLoginPwd(MD5Util.getMD5(loginPwd));
        //获得查询出的user对象
        try{
            User user=userService.login(us);
            request.getSession().setAttribute("user",user);
            map.put("success",true);
            //程序正常走到这里说明没有抛出异常
        } catch (Exception e){
            e.printStackTrace();
            String message=e.getMessage();
            map.put("success",false);
            map.put("message",message);
        }
        return map;
    }

}
