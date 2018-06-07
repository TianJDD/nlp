package com.ultra.nlp.mainsite.util;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;

/**
 * created by JIT on 2018/5/18
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    {
        System.out.println("初始化块");
    }
    public int count=0;//记录session的数量
    //监听session的创建,synchronized 防并发bug
    public void sessionCreated(HttpSessionEvent arg0) {
        System.out.println("【HttpSessionListener监听器】count++  增加");
        count++;
        arg0.getSession().getServletContext().setAttribute("count", count);

    }
    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {//监听session的撤销
        System.out.println("【HttpSessionListener监听器】count--  减少" + new Date());
        count--;
        arg0.getSession().getServletContext().setAttribute("count", count);
    }

}
