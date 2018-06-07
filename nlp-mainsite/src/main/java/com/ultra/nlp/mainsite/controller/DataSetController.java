package com.ultra.nlp.mainsite.controller;

import com.ultra.nlp.mainsite.model.JsonModel;
import com.ultra.nlp.mainsite.model.Page;
import com.ultra.nlp.mainsite.model.ReturnCode;
import com.ultra.nlp.mainsite.service.DataSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * created by JIT on 2018/4/24
 */
@RestController
@RequestMapping("/dataset")
public class DataSetController {

    private final static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    DataSetService dataSetService;

    /**
     * 获取dataset的一级列表，测试数据，行业数据
     *
     * @param request
     * @param response
     * @return
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.GET},
            value = "/type1")
    @ResponseBody
    public Object getDataSetType1(HttpServletRequest request, HttpServletResponse response) {

        try {
            JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(), dataSetService.getDataSetType1());
            response.setHeader("Access-Control-Allow-Origin", "*");
            return json;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JsonModel json = new JsonModel(true, ReturnCode.ERROR_CODE_11001.getValue(), ReturnCode.ERROR_CODE_11001.getKey(), null);
            return json;
        }

    }
    //获取二级数据列表
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.GET},
            value = "/type2")
    public Object getDataSetType2(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam("id") Integer id) {

        try {
            JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(), dataSetService.getDataSetType2(id));
            response.setHeader("Access-Control-Allow-Origin", "*");
            return json;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JsonModel json = new JsonModel(true, ReturnCode.ERROR_CODE_11001.getValue(), ReturnCode.ERROR_CODE_11001.getKey(), null);
            return json;
        }

    }

    /**
     * 数据集行业数据列表
     * 可以根据数据集大分类查询列表，如果行业领域分类id不为空，则查询大分类下的行业领域分类的数据集列表
     * 同时满足按照数据集标题或者内容进行模糊查询，排序规则默认按照数据集添加时间倒序排序，还可以按照下
     * 载次数和收录时间进行正序或者倒序排序
     *
     * @param attr     第一级分类（大分类）id
     * @param typeId   第二级分类（行业领域分类）id
     * @param request
     * @param response
     * @param pageNow
     * @param pageSize
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS, RequestMethod.POST, RequestMethod.GET},
            value = "/datasetlist/list")
    @ResponseBody
    public Object getdatasetlist(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "attr", required = false) String attr,
                                 @RequestParam(value = "typeId", required = false) String typeId,
                                 @RequestParam(value = "keyword", required = false) String keyword,
                                 @RequestParam(value = "useNum", required = false) Integer useNum,
                                 @RequestParam(value = "relTime", required = false) Integer relTime,
                                 @RequestParam(value = "pageNow", required = false) Integer pageNow,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        Map map = new HashMap<String, Object>();
        map.put("attr", attr);
        if (null != typeId && !"null".equals(typeId)) {
            map.put("id", typeId);
        }
        map.put("keyword", keyword);
        map.put("useNum", useNum);
        map.put("relTime", relTime);
        Page page = new Page();
        if (pageNow != null && pageSize != null) {
            page.setPageNow(pageNow);
            page.setPageSize(pageSize);
            map.put("queryStart", page.getQueryStart());
            map.put("pageSize", page.getPageSize());
        } else {
            page.setPageNow(1);
            page.setPageSize(0);
        }
        page.setParam(map);
        try {
            dataSetService.wholelist(page);
            JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(), page.getResultList());
            json.setPageSize(page.getPageSize());
            json.setPageCount(page.getPageCount());
            json.setTotal(page.getRowCount());
            return json;
        } catch (Exception e) {
            JsonModel json = new JsonModel(false, ReturnCode.ERROR_CODE_11001.getValue(), ReturnCode.ERROR_CODE_11001.getKey(), null);
            return json;
        }
    }
}

