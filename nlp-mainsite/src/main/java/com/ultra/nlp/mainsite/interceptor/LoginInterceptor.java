package com.ultra.nlp.mainsite.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * @Auther: admin
 * @Date: 2018/5/12 12:24
 * @Description:
 * @Usefor:
 * @param:
 * @Response:
 */
@Configuration
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter implements HandlerInterceptor  {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("ThreadLocal StartTime");


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        long beginTime = System.currentTimeMillis();//1、开始时间
        logger.info("********开始计时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS")
                .format(beginTime), httpServletRequest.getRequestURI());
        startTimeThreadLocal.set(beginTime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        long beginTime = startTimeThreadLocal.get();//得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis(); 	//2、结束时间
        logger.info("********结束计时:"+new SimpleDateFormat("hh:mm:ss.SSS")
                .format(endTime));
        logger.info("接口访问耗时："+(endTime-beginTime) + "ms");
    }
}
