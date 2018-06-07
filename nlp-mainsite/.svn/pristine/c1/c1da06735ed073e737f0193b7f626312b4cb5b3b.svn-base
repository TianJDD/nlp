package com.ultra.nlp.mainsite.util;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.UUID;

/**
 * created by JIT on 2018/4/28
 */
//登录校验工具类
public class LoginCheck {

    //生成token并放入session中
    public static String createToken(HttpServletRequest request, HttpServletResponse response) {
        String uuid = String.valueOf(UUID.randomUUID());
        request.getSession().setAttribute("X-Token",uuid);
        return uuid;
    }

    //获取request中的token并和本地的token进行比对
    /*public static boolean checkToken(HttpServletRequest request, HttpServletResponse response){
        if (request.getHeader("X-Token")!=null && request.getHeader("X-Token").equals(token)){
            return true;
        } else {
            return false;
        }
    }*/
}
