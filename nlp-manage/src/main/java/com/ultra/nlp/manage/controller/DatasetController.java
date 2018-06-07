package com.ultra.nlp.manage.controller;

import com.ultra.nlp.manage.model.JsonModel;
import com.ultra.nlp.manage.model.NlpData;
import com.ultra.nlp.manage.model.Page;
import com.ultra.nlp.manage.model.ReturnCode;
import com.ultra.nlp.manage.service.DataSetService;
import com.ultra.nlp.manage.util.GenerateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * created by JIT on 2018/4/12
 */
@RestController
@RequestMapping("/dataset")
public class DatasetController {

    private final static Logger logger = LoggerFactory.getLogger(DatasetController.class);

    @Autowired
    DataSetService dataSetService;


    /**
     * 数据集属性添加接口
     * 修改初衷：因为nlp_data_attr表弃用，对之前开发做出修改。
     * 设计思路：使用nlp_data_type表实现添加属性
     * @param name
     * @return
     * @date 2018/5/11 15:06
     * last modified by JIT
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST},value = "/attr/add")
    @ResponseBody
    public Object addAttr(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "attr")String attr,
                          @RequestParam(value = "icon")String icon,
                          @RequestParam(value="name") String name){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> map = new HashMap<>(5);
        map.put("name",name);
        map.put("icon",icon);
        if (null != attr && attr != ""){
            map.put("attr",attr);
        }
        try{
            int i = dataSetService.addDataAttr(map);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            if (i > 0){
                json.setData("添加成功");
            }else {
                json.setData("添加失败");
            }
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),"添加失败");
            return json;
        }
    }

    /**
     * 删除数据集属性接口
     * @param id
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST},value = "/attr/del")
    @ResponseBody
    public Object delAttr(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value="id",required =true) String id){
        response.setHeader("Access-Control-Allow-Origin", "*");
        try{
            dataSetService.delDataAttr(Integer.parseInt(id));
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }


    /**
     * 添加数据集接口
     * @param request
     * @param response
     * @param name
     * @param attr
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST},value = "/data/add")
    @ResponseBody
    public Object addData(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value="name",required =true) String name,
                          @RequestParam(value="uploader",required =true) String uploader,
                          @RequestParam(value="introduce",required =true) String introduce,
                          @RequestParam(value="size",required =true) String size,
                          @RequestParam(value="format",required =true) String format,
                          @RequestParam(value="url",required =true) String url,
                          @RequestParam(value="typeId",required =true) String typeId,
                          @RequestParam(value="img",required =false) String img,
                          @RequestParam(value="attr",required =true) String attr){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> map = new HashMap<>(10);
        try{
            map.put("name",name);
            map.put("uploader",uploader);
            map.put("introduce",name);
            map.put("size",size);
            map.put("format",format);
            map.put("url",url);
            map.put("typeId",Integer.parseInt(typeId));
            map.put("img",img);
            map.put("attr",Integer.parseInt(attr));
            logger.info("传入参数为：" + map.toString());
            dataSetService.addData(map);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     * 删除数据集接口
     * @param request
     * @param response
     * @param id
     * @return
     * @date 2018/5/10 11:18
     * last modified by JIT
     */ 
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST},value = "/data/del")
    @ResponseBody
    public Object delData(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value="id",required =true) String id){
        response.setHeader("Access-Control-Allow-Origin", "*");
        try{
            logger.info("传入id为：" + id);
            dataSetService.delData(Integer.parseInt(id));
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /********************************************************************************/

    /**
     * 获取属性列表（一级菜单），查询表nlp_data_type。对应dao层接口名getDataAttrList
     * 查询条件，id，当前页，当前页大小
     * 逻辑，查询当前页数据集（一级）属性列表
     * 结果，带有分页功能的数据集（一级）属性列表
     * @param request
     * @param response
     * @param id
     * @param pageNow
     * @param pageSize
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},value = "/attrlist")
    public Object getDataAttrs(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "id",required=false) Integer id,
                               @RequestParam(value = "pageNow",required=false) Integer pageNow,
                               @RequestParam(value = "pageSize",required=false) Integer pageSize){
        Map map = new HashMap<String,Object>();
        Page page = new Page();
        if (null != id){
            //map = new HashMap<String,String>();
            map.put("id",id);
        }
        if(pageNow != null && pageSize != null){
            page.setPageNow(pageNow);
            page.setPageSize(pageSize);
            map.put("queryStart",page.getQueryStart());
            map.put("pageSize",page.getPageSize());
        }else{
            page.setPageNow(1);
            page.setPageSize(0);
        }
        page.setParam(map);
        page = dataSetService.getDataAttr(page);
        JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
        json.setPageSize(pageSize);
        json.setPageCount(page.getPageCount());
        json.setTotal(page.getRowCount());
        response.setHeader("Access-Control-Allow-Origin", "*");
        //System.out.println(page.getPageSize()+"----"+page.getPageNow());
        return json;
    }

    /**
     * 获取分类列表（二级菜单）
     * @param request
     * @param response
     * @param id
     * @param pageNow
     * @param pageSize
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},value = "/dataTypelist")
    public Object getDataTypes (HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "id",required=false) Integer id,
                                @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                @RequestParam(value = "pageSize",required=false) Integer pageSize){
        Map map = new HashMap<String,Object>();
        Page page = new Page();
        if (null != id){
            //map = new HashMap<String,String>();
            map.put("id",id);
        }
        if(pageNow != null && pageSize != null){
            page.setPageNow(pageNow);
            page.setPageSize(pageSize);
            map.put("queryStart",page.getQueryStart());
            map.put("pageSize",page.getPageSize());
        }else{
            page.setPageNow(1);
            page.setPageSize(0);
        }
        page.setParam(map);
        page = dataSetService.getDateTypeList(page);
        JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
        json.setPageSize(pageSize);
        json.setPageCount(page.getPageCount());
        json.setTotal(page.getRowCount());
        response.setHeader("Access-Control-Allow-Origin", "*");
        //System.out.println(page.getPageSize()+"----"+page.getPageNow());
        return json;
    }

    /**
     * 获取三级列表(从nlp_data查询数据列表)
     * @param request
     * @param response
     * @param id
     * @param pageNow
     * @param pageSize
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},value = "/datalist")
    public Object getDataList (HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "id",required=false) Integer id,
                               @RequestParam(value = "pageNow",required=false) Integer pageNow,
                               @RequestParam(value = "pageSize",required=false) Integer pageSize){
        Map map = new HashMap<String,Object>();
        Page page = new Page();
        if (null != id){
            //map = new HashMap<String,String>();
            map.put("id",id);
        }
        if(pageNow != null && pageSize != null){
            page.setPageNow(pageNow);
            page.setPageSize(pageSize);
            map.put("queryStart",page.getQueryStart());
            map.put("pageSize",page.getPageSize());
        }else{
            page.setPageNow(1);
            page.setPageSize(0);
        }
        page.setParam(map);
        page = dataSetService.getDataList(page);
        JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
        json.setPageSize(pageSize);
        json.setPageCount(page.getPageCount());
        json.setTotal(page.getRowCount());
        response.setHeader("Access-Control-Allow-Origin", "*");
        //System.out.println(page.getPageSize()+"----"+page.getPageNow());
        return json;
    }

    /**
     * 修改属性（一级）
     * @param request
     * @param response
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/updateAttr")
    public Object updateAttr(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "id",required=false) Integer id,
                             @RequestParam(value = "name",required=false) String name,
                             @RequestParam(value = "icon",required = false)String icon
    ){
        response.setHeader("Access-Control-Allow-Origin", "*");
        try{
            dataSetService.updateDataAttr( id,name,icon);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            return json;
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }


    /**
     * 修改展示的数据（三级）
     * @param request
     * @param response
     * @param nlpData
     * @return
     */
    @RequestMapping("/updateData")
    public Object updateData(HttpServletRequest request, HttpServletResponse response,@ModelAttribute NlpData nlpData){
        response.setHeader("Access-Control-Allow-Origin", "*");
        try{
            dataSetService.updateData(nlpData);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            return json;
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }

    }

}
