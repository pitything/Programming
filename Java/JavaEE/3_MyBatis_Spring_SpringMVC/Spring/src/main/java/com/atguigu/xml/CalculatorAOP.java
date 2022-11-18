package com.atguigu.xml;

// @Aspect表示这个类是一个切面类

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// @Component注解保证这个切面类能够放入IOC容器
@Component
public class CalculatorAOP {
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println("Logger-->前置通知，方法名：" + methodName + "，参 数：" + args);
    }

    public void afterMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->后置通知，方法名：" + methodName + "，参 数：" + args);
    }

    public void afterReturningMethod(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        System.out.println("Logger-->返回通知，方法名：" + methodName + "，返回值：" + result);
    }

    public void exceptionMethod(JoinPoint joinPoint, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("Logger-->异常通知，方法名：" + methodName + "，异常信息：" + exception.getMessage());
    }

    public Object around(ProceedingJoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Object result = null;
        try{
            System.out.println("环绕通知-->前置通知，方法名：" + methodName + "，参 数：" + args);
            result = joinPoint.proceed();
            System.out.println("环绕通知-->返回通知，方法名：" + methodName + "，返回值：" + result);
        }catch (Throwable exception){
            System.out.println("环绕通知-->异常通知，异常信息：" + exception.getMessage());
        } finally {
            System.out.println("环绕通知-->后置通知，方法名：" + methodName + "，返回值：" + result);
        }
        return result;
    }
}