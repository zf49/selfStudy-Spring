package com.kuang.diy;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//使用注解来实现
@Aspect  //标注这是一个切面
public class AnnotationPointCut {

    @Before("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("方法执行前");
    }

     @After("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("方法执行hou");
    }

     @Around("execution(* com.kuang.service.UserServiceImpl.*(..))")
     //环绕中，我们需要给定一个参数，代表我们要获取处理切入的点
    public void around(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("环绕前");
         System.out.println(jp.getSignature());
         Object proceed = jp.proceed();
         System.out.println("环绕后");

     }


}
