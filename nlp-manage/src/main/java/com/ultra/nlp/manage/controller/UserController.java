package com.ultra.nlp.manage.controller;

import com.ultra.nlp.manage.model.JsonModel;
import com.ultra.nlp.manage.model.ReturnCode;
import com.ultra.nlp.manage.model.User;
import com.ultra.nlp.manage.model.UserAdd;
import com.ultra.nlp.manage.service.IUserService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "user")
public class UserController {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    IUserService userService;

    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},value = "login")
    @ResponseBody
    public Object login(@RequestParam(value="name",required =true) String username,
                        @RequestParam(value="password",required =true)String password){
        Map<String,Object> map = new HashMap<>(11);
        map.put("name",username);
        map.put("password",password);
        try{
            User user = userService.checkIfExist(map);
            if(null != user){//用户存在
                if(user.getPassword().equals(password)){//密码正确
                    UUID uuid = java.util.UUID.randomUUID();
                    String token = uuid.toString();
                    map.clear();
                    map.put("tocken",token);
                    JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),map);
                    return json;
                }else{
                    JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_0002.getValue(),ReturnCode.ERROR_CODE_0002.getKey(),null);
                    return json;
                }

            }else{
                JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_0001.getValue(),ReturnCode.ERROR_CODE_0001.getKey(),null);
                return json;
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }

    }

    /**
     * 用户添加
     * @param request
     * @param response
     * @param useradd
     * 应用场景：用于添加服务
     * 逻辑：前端将需要添加的参数请求发送给后台，控制器在后台用对象useradd 接收，
     * 将请求发送到业务层处理业务，业务处理成功后，不需要向前端返回json
     * 结果：添加用户成功/添加用户失败并打印日志。
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "UserAdd")
    @ResponseBody
    public Object UserAdd(HttpServletRequest request, HttpServletResponse response,
                         @RequestBody UserAdd useradd){
        Map<String, Object> map = new HashMap<>(11);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            useradd.setCreateTime(sdf.format(date));
            useradd.setUpdateTime(sdf.format(date));
            //用户添加成功
            userService.usertoadd(useradd);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);//用户添加失败，并打印失败日志
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

}
