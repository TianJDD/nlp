package com.ultra.nlp.manage.aspect;

import com.ultra.nlp.manage.model.JsonModel;
import com.ultra.nlp.manage.service.IUserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LoginCheckAspect {
    @Pointcut("execution(public * com.ultra.nlp.manage.controller.*.*(..))")
    public  void LoginCheck(){}
    @Resource
    IUserService userService;
    @Before("LoginCheck()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //校验用户token是否过期
        System.out.println("开始走切面");


    }

    @AfterReturning(returning = "json", pointcut = "LoginCheck()")
    public void doAfterReturning(JsonModel json) throws Throwable {
        // 处理完请求，返回内容,目前没有

    }

    //后置异常通知
    @AfterThrowing("LoginCheck()")
    public void throwss(JoinPoint point){
        System.out.println("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("LoginCheck()")
    public void after(JoinPoint point){
        System.out.println("方法最后执行.....");
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("LoginCheck()")
    public Object arround(ProceedingJoinPoint pjp) {
        try {
            Object o =  pjp.proceed();
            System.out.println("方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

}
