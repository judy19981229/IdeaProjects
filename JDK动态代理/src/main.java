import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class main {
    public static void main(String[] args) {
        Subject subject=new RealSubject();
        InvocationHandler handler=new Handler(subject);
        ClassLoader loader=subject.getClass().getClassLoader();
        Class[] interfaces=subject.getClass().getInterfaces();
        Subject realSubject=(Subject) Proxy.newProxyInstance(loader,interfaces,handler);
        String hello=realSubject.sayHello("Judy");
        System.out.println(hello);
        String goodBye=realSubject.sayGoodBye("Judy");
        System.out.println(goodBye);
    }
}
