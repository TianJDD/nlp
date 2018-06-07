package com.ultra.nlp.mainsite.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 配置切面，对需要打印日志的类或者方法，进行切面处理
 */
@Aspect
@Component
public class LoggerAspect {
    private final static Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
    //目前只对controller做日志输出
    @Pointcut("execution(public * com.ultra.nlp.mainsite.controller.*.*(..))")
    public  void addLogger(){}

    @Before("addLogger()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //打印url
        logger.info("请求URL ：url = {}",request.getRequestURL());
        //打印方法
        logger.info("请求方法：request_method = {}",request.getMethod());
        //打印请求客户端ip
        logger.info("客户端IP：ip = {}",request.getRemoteAddr());
        //打印请求类方法
        logger.info("请求类方法：method = {}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //打印请求参数
        logger.info("请求参数：args[] = {}",joinPoint.getArgs());
    }
    /*@After("addLogger()")
    public void doAfter() {

    }*/
    @AfterReturning(returning = "object",pointcut = "addLogger()")
    public void doAfterReturning(Object object) throws Throwable {
        //打印返回结果
        logger.info("返回结果：response = {}",object);
    }

    //后置异常通知
    /*@AfterThrowing("addLogger()")
    public void throwss(JoinPoint point){

    }*/
    //环绕通知,环绕增强，相当于MethodInterceptor
    /*@Around("addLogger()")
    public Object arround(ProceedingJoinPoint pjp) {

        return null;
    }*/
}
