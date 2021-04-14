package handler;

import exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LoginException.class)
    public Map<String,Object> LoginException(Exception exception){
        Map<String,Object> map=new HashMap<>();
        map.put("success",false);
        String message= exception.getMessage();
        map.put("message",message);
        return map;
    }
}

