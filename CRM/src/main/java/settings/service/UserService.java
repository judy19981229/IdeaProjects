package settings.service;


import exception.LoginException;
import settings.entity.User;

public interface UserService {
    User login(User us) throws LoginException;
}
