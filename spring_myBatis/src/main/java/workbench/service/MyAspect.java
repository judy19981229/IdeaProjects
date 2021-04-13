package workbench.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Aspect
public class MyAspect {
    @Around("execution(* workbench.service.StudentServiceImpl.*(..))")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("前置:"+new Date());
        Object result=proceedingJoinPoint.proceed();
        System.out.println("后置:");
        return result;
    }
}
