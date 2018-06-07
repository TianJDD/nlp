package com.ultra.nlp.mainsite.controller;

import com.ultra.nlp.mainsite.model.*;
import com.ultra.nlp.mainsite.service.IServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 平台服务 相关接口
 */
@Controller
@RequestMapping(value = "service")
public class ServiceController {

    private final static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    IServiceService serviceService;


    /**
     * 导航栏平台服务列表展示
     * 测试通
     * @param request
     * @param response
     * @param pageNow
     * @param pageSize
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/type/headList")
    @ResponseBody
    public Object getServiceList(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                 @RequestParam(value = "pageSize",required=false) Integer pageSize
    ){
        //查询所有的服务类别，然后按照层级结构返回
        Map map = new HashMap<String,Object>();
        Page page = new Page();
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
        try{
            serviceService.getServiceTypeList(page);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
            json.setPageSize(pageSize);
            json.setPageCount(page.getPageCount());
            json.setTotal(page.getRowCount());
            response.setHeader("Access-Control-Allow-Origin", "*");
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }

    }

    /**
     * 查询基础热门服务下的第一级
     * 测通
     * @param request
     * @param response
     * @param pageNow
     * @param pageSize
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/type/recList1")
    @ResponseBody
    public Object getRecServiceList1(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                    @RequestParam(value = "pageSize",required=false) Integer pageSize
    ){

        Page page = new Page();
        Map map = new HashMap<String,Object>();
        page.setPage(map,pageNow,pageSize,page);
        try{
            serviceService.getRecServiceList1(page);

            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
            json.setPageSize(pageSize);
            json.setPageCount(page.getPageCount());
            json.setTotal(page.getRowCount());
            response.setHeader("Access-Control-Allow-Origin", "*");
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }

    }


    /**
     * 查询基础热门服务第二级
     * 测试通过
     * @param request
     * @param response
     * @return
     */
    @CrossOrigin(methods = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET })
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/type/recList2")
    @ResponseBody
    public Object getRecServiceList2(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "id",required = false)String id,
                                @RequestParam(value = "size",required = false)Integer size,
                                 @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                 @RequestParam(value = "pageSize",required=false) Integer pageSize
    ){
        Page page = new Page();
        Map map = new HashMap<String,Object>();
        map.put("id",id);
        if (size == null || size == 0){
            size = 3;
        }
        map.put("size",size);
        logger.info("传入参数id为："+id+"，size为："+size);
        page.setParam(map);
        try{
            serviceService.getRecServiceList2(page);

            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
            json.setPageSize(page.getPageSize());
            json.setPageCount(page.getPageCount());
            json.setTotal(page.getRowCount());
            //response.setHeader("Access-Control-Allow-Origin", "*");
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }

    }

    /**
     * 查询应用场景下的热门服务（只有一级）
     * @param request
     * @param response
     * @param pageNow
     * @param pageSize
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/type/recClslist")
    @ResponseBody
    public Object getRecClsList(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                  @RequestParam(value = "pageSize",required=false) Integer pageSize
    ){
        Page page = new Page();
        Map map = new HashMap<String,Object>();
        page.setPage(map,pageNow,pageSize,page);
        try{
            serviceService.getRecClsList(page);

            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
            json.setPageSize(pageSize);
            json.setPageCount(page.getPageCount());
            json.setTotal(page.getRowCount());
            response.setHeader("Access-Control-Allow-Origin", "*");
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     *根据第三级type3Id查询service列表
     * @param request
     * @param response
     * @param id
     * @param pageNow
     * @param pageSize
     * @return
     */
    @CrossOrigin(methods = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET })
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/type/serviceList")
    @ResponseBody
   public Object getServiceListByType3Id(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "id",required = false)String id,
                                 @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                 @RequestParam(value = "pageSize",required=false) Integer pageSize
    ){
        Page page = new Page();
        Map map = new HashMap<String,Object>();
        page.setPage(map,id,pageNow,pageSize,page);
        try{
            serviceService.getServiceListByType3Id(page);

            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
            json.setPageSize(pageSize);
            json.setPageCount(page.getPageCount());
            json.setTotal(page.getRowCount());
            //response.setHeader("Access-Control-Allow-Origin", "*");
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }
    /**
     * 服务列表页模糊查询
     * 可以根据服务所属大类查询服务列表，也可以在服务大类下按照服务类别名称或者服务名称模糊查询
     * 查询服务列表，同时满足按照服务使用次数和服务接入时间进行排序
     * @param request
     * @param response
     * @param keyword
     * @param class_id
     * @param useNum
     * @param relTime
     * @param pageNow
     * @param pageSize
     * @return
     */
    @CrossOrigin(methods = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET})
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/searchServiceByCondition")
    @ResponseBody
    public Object searchServiceByCondition(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "keyword",required=false) String keyword,
                                @RequestParam(value = "class_id",required=false) String class_id,
                                @RequestParam(value = "useNum",required=false) Integer useNum,//1:倒序排列  2：升序排列
                                @RequestParam(value = "relTime",required=false) Integer relTime,//1：倒序排列  2：升序排列
                                @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                @RequestParam(value = "pageSize",required=false) Integer pageSize
    ){
        logger.info("接收到的参数为 : class_id="+class_id +"keyword=" + keyword  +"useNum=" + useNum  +"relTime=" + relTime +"pageNow=" + pageNow+"pageSize=" + pageSize);
        Page page = new Page();
        Map map = new HashMap<String,Object>();
        if(null == keyword  || "null".equals(keyword)){
            map.put("keyword","");
        }else{
            map.put("keyword",keyword);
        }
        if(class_id != null && ! "".equals(class_id)){
            map.put("class_id",class_id);
        }
        if(useNum != null) map.put("useNum",useNum);
        if(relTime != null) map.put("relTime",relTime);
        try{
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
            logger.info("接收到的参数为"+ map.toString());
            page = serviceService.searchServiceByCondition(page);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
            json.setPageSize(pageSize);
            json.setPageCount(page.getPageCount());
            json.setTotal(page.getRowCount());
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }


    /**
     * 查询服务详情相关信息接口
     * @param id  对应服务ID
     * @return    服务详情信息
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.GET
            ,RequestMethod.POST},value = "/details/list")
    @ResponseBody
    public Object detailslist(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "id",required = false)String id){
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            Map map = new HashMap();
            map.put("id",id);
            ServiceDetail nlpService = serviceService.detailslist(map);
            logger.info("/details/list    request response 服务详情：",nlpService.toString());
            JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(),nlpService);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }
    /**
     * 根据服务分类id查询分类名称
     * 用于服务详情页展示服务信息中的服务所属分类名称
     * @param class_id
     * @return
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.GET
            ,RequestMethod.POST},value = "/getServiceTypeNameById")
    @ResponseBody
    public Object getServiceTypeNameById(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "class_id",required = false)String class_id){
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            Map map = new HashMap<String,Object>(1);
            map.put("class_id",class_id);
            map = serviceService.getServiceTypeNameById(map);
            JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(),map);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     * 点解查看服务详情时根据服务id查询服务跳转方式，返回跳转链接
     * @param request
     * @param response
     * @param serviceId
     * @return
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.GET
            ,RequestMethod.POST},value = "/detail/forward")
    @ResponseBody
    public Object checkServiceDetailForward(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam(value = "serviceId",required = false)String serviceId){
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            Map map = new HashMap<String,Object>(1);
            map.put("serviceId",serviceId);
            map = serviceService.checkServiceDetailForward(map);
            JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(),map);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

}
