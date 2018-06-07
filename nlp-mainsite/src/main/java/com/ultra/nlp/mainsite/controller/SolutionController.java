package com.ultra.nlp.mainsite.controller;

import com.ultra.nlp.mainsite.model.JsonModel;
import com.ultra.nlp.mainsite.model.Page;
import com.ultra.nlp.mainsite.model.ReturnCode;
import com.ultra.nlp.mainsite.service.ISolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 解决方案 相关接口
 */
@Controller
@RequestMapping(value = "solutions")
public class SolutionController {

    private final static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    ISolutionService solutionService;

    /**
     * 查询解决方案列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/getSolutionList")
    @ResponseBody
    public Object getSolutionList(HttpServletRequest request, HttpServletResponse response
    ){
        Page page = new Page();
        try{
            List list = solutionService.getSolutionList();
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),list);
            response.setHeader("Access-Control-Allow-Origin", "*");
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }
}
