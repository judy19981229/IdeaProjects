package settings.service;

import exception.LoginException;
import settings.dao.UserDao;
import org.springframework.stereotype.Service;
import settings.entity.User;
import utils.DateTimeUtil;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{

    @Resource
    UserDao userDao;

    @Override
    public User login(User us) throws LoginException {
        User user=userDao.login(us);
        if(user==null){
            throw new LoginException("账号密码错误，请重试");
        } else{
            //判断失效时间
            String expireTime=user.getExpireTime();
            String currentTime= DateTimeUtil.getSysTime();
            if(expireTime.compareTo(currentTime)<0){
                throw new LoginException("账号已失效");
            }
            //判断锁定状态
            String lockState=user.getLockState();
            if("0".equals(lockState)){
                throw new LoginException("账号已被锁定");
            }
            //判断ip地址
            String userIp=us.getAllowIps() ;
            String allowIps=user.getAllowIps();
            System.out.println(userIp);
            System.out.println("-----");
            System.out.println(allowIps);
            if(!allowIps.contains(userIp)){
                throw new LoginException("ip地址不允许访问");
            }
        }
        return user;
    }
}
