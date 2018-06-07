package com.ultra.nlp.mainsite.controller;

import com.ultra.nlp.mainsite.model.JsonModel;
import com.ultra.nlp.mainsite.model.ReturnCode;
import com.ultra.nlp.mainsite.service.indexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by JIT on 2018/5/23
 */
@RestController
@RequestMapping("/index")
public class indexController {
    private final static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private indexService indexService;
    /*
    * 说明，对应于前台展示页面上的专家，机构等数量的获取，设计初衷，通过前台传type来达到区分，
    * 进而调用不同的sql去不同的表中进行查询。但是，当前版本，前台不用传标识，后期需要，可以进行添加，
    * 并进行相应处理。
    * @date 2018/5/23 15:21
    * last modified by JIT
    */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.GET},
            value = "/getCountBytype")
    public Object getCountBytype(HttpServletRequest request, HttpServletResponse response ) {
        try {
            JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(), indexService.getCountBytype());
            response.setHeader("Access-Control-Allow-Origin", "*");
            return json;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JsonModel json = new JsonModel(true, ReturnCode.ERROR_CODE_11001.getValue(), ReturnCode.ERROR_CODE_11001.getKey(), null);
            return json;
        }

    }
}
