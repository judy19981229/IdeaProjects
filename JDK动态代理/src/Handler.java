import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Handler implements InvocationHandler {
    private Object target;

    public Handler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("在调用之前，我要干点啥呢？");
        System.out.println("Method:" + method);
        Object result=method.invoke(target,args);
        System.out.println("在调用之后，我要干点啥呢？");
        return result;
    }
}
