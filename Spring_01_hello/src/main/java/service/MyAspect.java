package service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

//切面类需要加上@Aspect注解
@Component
@Aspect
public class MyAspect {
    //切面类中的方法（通知方法）是实现切面功能的
    //必须是公共方法public、没有返回值、可以有参数（但参数不是自定义的）

    //@Before是前置通知注解，在目标方法之前执行，不会影响目标方法
    //属性：value，切入点表达式，表示切面的功能执行的位置
    //通知方法中的参数：JoinPoint（业务方法，要加入切面功能的业务方法）
    //可以在通知方法中获取方法执行时的信息，例如方法名称、方法实参
    //如果你的切面功能中需要用到方法的信息，就加入JoinPoint
    //这个JoinPoint参数是由框架赋予的，必须是第一个位置的参数
    @Before("execution(* service.ServiceImpl.doSome(..))")
    public void myBefore(JoinPoint joinPoint){
        //获取方法的完整定义
        System.out.println("方法的签名(定义)="+joinPoint.getSignature());
        System.out.println("方法的名称="+joinPoint.getSignature().getName());
        Object[] args= joinPoint.getArgs();
        for(Object obj:args){
            System.out.println("参数="+obj);
        }
        System.out.println("前置通知：在目标之前输出执行时间:");
        System.out.println(new Date());
    }

    //后置通知定义方法，方法是实现切面功能的
    //方法的定义要求：1.公共方法public 2.没有返回值 3.有Object类的参数
    //@AfterReturning是后置通知注解，属性：1.value（切入点表达式）
    //2.returning 自定义的变量，表示目标方法返回值
    //自定义变量名必须和通知方法的形参名一样(这里的两个res必须要一样）
    //特点：能够获取到目标方法的返回值，根据返回值做不同的处理功能
    //先执行目标方法，再执行后置通知，传入返回值，因此可以修改这个返回值
    @AfterReturning(value="execution(* service.ServiceImpl.doSome(..))",
            returning="res")
    public void myAfterReturning(JoinPoint joinPoint,Object res){
        //Object res是目标方法执行后的返回值，根据返回值做你切面的功能处理
        System.out.println(joinPoint.getSignature());
        System.out.println("后置通知：执行事务，返回值是"+res);
    }

    //环绕通知方法的定义格式：1.public 2.返回值（Object obj）
    //3.方法有参数，固定的参数ProceedingJoinPoint
    //@Around是环绕通知注解，属性：value 切入点表达式("execution()")
    //特点：1.功能最强的通知，在目标方法的前和后都能增强功能
    //2.控制目标方法是否被调用执行 3.修改原来目标方法的执行结果，影响最后的调用结果
    //环绕通知等同于jdk动态代理的invocationHandler接口
    //参数：ProceedingJoinPoint等同于动态代理中的Method，执行目标方法
    //返回值：目标方法的执行结果，可以被修改
    //环绕通知实际上就是把doOther方法替换成了myAround方法，经常做事物
    //在目标方法之前开启事物，执行目标方法，在目标方法之后提交事物
    @Around("mypt()")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //实现环绕通知
        //1.目标方法调用,等同于method.invoke()
        System.out.println("环绕通知的时间，前置：");
        System.out.println(new Date());
        //环绕通知可以控制方法是否执行
        Object[] args= proceedingJoinPoint.getArgs();
        Object result=null;
        if(args[0]=="alien"){
            result=proceedingJoinPoint.proceed();
        }
        System.out.println("环绕通知的返回值，后置：");
        System.out.println(result);
        return result;
    }

    @Pointcut("execution(* service.ServiceImpl.doOther(..))")
    private void mypt(){}
}
