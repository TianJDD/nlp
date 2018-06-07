package com.ultra.nlp.mainsite.controller;


import com.ultra.nlp.mainsite.model.JsonModel;
import com.ultra.nlp.mainsite.model.ReturnCode;
import com.ultra.nlp.mainsite.model.User;
import com.ultra.nlp.mainsite.service.userService;
import com.ultra.nlp.mainsite.util.JedisPoolUtils;
import com.ultra.nlp.mainsite.util.LoginCheck;
import com.ultra.nlp.mainsite.util.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.*;
import java.net.URLEncoder;
import java.util.logging.Logger;

/**
 * created by JIT on 2018/4/27
 */
@RestController
@RequestMapping("/sso")
public class userController {

    @Autowired
    private userService userService;

    Jedis jedis = null;
    JedisPoolConfig c = new JedisPoolConfig();
    JedisPool jedisPool = new JedisPool(c,"192.168.95.57", 6379); // 初始化连接池

    /**
     * 登录
     * @param request
     * @param response
     * @param name
     * @param password
     * @return
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/login")
    @ResponseBody
    public Object login(HttpServletRequest request,HttpServletResponse response,
                        @RequestParam("name") String name,
                        @RequestParam("password") String password

    ) {

        User user = userService.selectUser(name, password);
        JsonModel jsonModel = null;
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60*60*24*7);
        //登录前进行token验证，token通过再进行登录验证
        if (null == user) {
            //用户名不存在
            jsonModel = new JsonModel(false, ReturnCode.ERROR_CODE_0001.getValue(), ReturnCode.ERROR_CODE_0001.getKey());
            return jsonModel;
        } else {
            //成功登录
            if (name.equals(user.getUserName()) && password.equals(user.getUserPwd())) {
                jsonModel = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey());
                session.setAttribute("name",name);
                session.setAttribute("id",user.getId());
                //登录成功后，生成唯一的用户标识X-Token，并通过response返回给前台
                String token = LoginCheck.createToken(request, response);
                try {
                    jedis = jedisPool.getResource();
                    jedis.set("X-Token_"+name,token);
                    System.out.println(jedis.get("X-Token_"+name));
                } catch (Exception e){
                    e.printStackTrace();
                } finally {

                }
                jsonModel.setData(token);

                return jsonModel;
            } else {
                if (!password.equals(user.getUserPwd())) {
                    //密码错误
                    jsonModel = new JsonModel(false, ReturnCode.ERROR_CODE_0002.getValue(), ReturnCode.ERROR_CODE_0002.getKey());
                }

                return jsonModel;
            }
        }
    }

    /**
     * 退出
     * @param request
     * @param response
     * @param name
     */
    @RequestMapping("/logout")
    public Object logout(HttpServletRequest request,HttpServletResponse response,
                       @RequestParam("name") String name){
        JsonModel jsonModel = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey());
        HttpSession session = request.getSession();
        session.removeAttribute("name");
        JedisPoolConfig c = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(c,"192.168.95.57", 6379); // 初始化连接池
        Jedis jedis = jedisPool.getResource();
        jedis.set("X-Token_"+name,null);
        session.invalidate();
        return jsonModel;
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @param name
     * @param OldPassword
     * @param NewPassword
     * @return
     */
    @RequestMapping("/updatePassword")
    public Object updatePassword(HttpServletRequest request,HttpServletResponse response,
                                 @RequestParam("name") String name,
                                 @RequestParam("OldPassword")String OldPassword,
                                 @RequestParam("NewPassword")String NewPassword){
        HttpSession session = request.getSession();
        String loginToken = (String) session.getAttribute("X-Token");
        jedis = jedisPool.getResource();
        String s = jedis.get("X-Token" + name);
        JsonModel jsonModel = null;
        if(null != loginToken && loginToken == s){
            int i = userService.updatePassword(name,OldPassword,NewPassword);
            if (i == 1){
                jsonModel = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey());
            } else {
                jsonModel = new JsonModel(true, ReturnCode.ERROR_CODE_11001.getValue(), ReturnCode.ERROR_CODE_11001.getKey());
            }
        }
        return jsonModel;
    }

    /**
     * 获取在线用户的个数
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/count")
    @ResponseBody
    public String number(HttpServletRequest request, HttpServletResponse response){
        try{  //把sessionId记录在浏览器
            String jsessionId = request.getSession().getId();
            //先设置cookie有效期为2天，不用担心，session不会保存2天
            request.getSession().setMaxInactiveInterval(10);
            System.out.println(jsessionId);
        }catch (Exception e){
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        Object count=session.getServletContext().getAttribute("count");
        return "count : "+count;
    }

}
