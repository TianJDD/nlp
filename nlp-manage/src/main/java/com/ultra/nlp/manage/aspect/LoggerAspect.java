package com.ultra.nlp.manage.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
public class LoggerAspect {
    //目前只对controller做日志输出
    @Pointcut("execution(public * com.ultra.nlp.manage.controller.*.*(..))")
    public  void addLogger(){}

    @Before("addLogger()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String>  headers =  request.getHeaderNames();
        StringBuffer buffer = new StringBuffer();
        while (headers.hasMoreElements()){
            String para = headers.nextElement();
            String paramValue = request.getParameter(para);
            buffer.append(paramValue);
        }
        Logger logger =  LoggerFactory.getLogger(LoggerAspect.class);
        logger.info("请求方法路径为：" + request.getRequestURI());
        logger.info("请求方法参数为：" + buffer);

    }
}
