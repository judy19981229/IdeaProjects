package service;

import dao.UserDao;
import entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserServiceImpl implements UserService{
    @Resource(name="userDao")
    UserDao userDao;

    @Override
    public int insertUser(User user) {
        int result=userDao.insertUser(user);
        return result;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl() {
    }
}
