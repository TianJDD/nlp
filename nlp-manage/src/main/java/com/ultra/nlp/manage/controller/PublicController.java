package com.ultra.nlp.manage.controller;

import com.ultra.nlp.manage.model.JsonModel;
import com.ultra.nlp.manage.model.ReturnCode;
import com.ultra.nlp.manage.service.PublicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Auther: admin
 * @Date: 2018/5/9 11:10
 * @Description: 公共接口，提供公共方法
 * @Usefor:
 * @param:
 * @Response:
 */


@Controller
@RequestMapping(value = "/public")
public class PublicController {
    private final static Logger logger = LoggerFactory.getLogger(PublicController.class);

    @Autowired
    PublicService publicService;
    /**
     *
     * 功能描述: 查询数据是否已存在接口
     * 根据传入类型判断是否数据已存在
     * @param: type 查询数据类型（所属表）
     *          academy - 大学  group - 机构/部门  professor - 教授  service - 服务  classify - 服务分类 user - 用户
     * @param: name 查询数据名称
     * @return: 查询数据结果
     * @auther: guyuefei
     * @date:
     */

    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},value = "/checkIfExist")
    @ResponseBody
    public Object checkIfExist(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value="type",required =false) String type,
                                 @RequestParam(value="name",required =false) String name){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> map = new HashMap<>();
        map.put("type",type);
        map.put("name",name);
        try{
            map =  publicService.checkIfExist(map);
            JsonModel json;
            if(map.isEmpty()){
                json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),true);
            }else{
                json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),false);
            }
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }

    }
}
