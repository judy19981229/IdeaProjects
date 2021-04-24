package settings.service;


import exception.LoginException;
import settings.entity.User;
import workbench.entity.Activity;

import java.util.List;

public interface UserService {
    User login(User us) throws LoginException;
    List<User> getUserList();

}
