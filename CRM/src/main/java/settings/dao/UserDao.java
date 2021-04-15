package settings.dao;

import settings.entity.User;

import java.util.List;

public interface UserDao {

    User login(User us);
    List<User> getUserList();
}
