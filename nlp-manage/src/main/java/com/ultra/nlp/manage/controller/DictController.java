package com.ultra.nlp.manage.controller;

import com.ultra.nlp.manage.model.JsonModel;
import com.ultra.nlp.manage.model.NlpDict;
import com.ultra.nlp.manage.model.Page;
import com.ultra.nlp.manage.model.ReturnCode;
import com.ultra.nlp.manage.service.DictService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
//@RequestMapping("/dict")
public class DictController {
    private final static Logger logger = LoggerFactory.getLogger(DictController.class);

    @Autowired
    DictService dictService;

    /**
     * 数据字典查询接口
     * @param dictCode
     * @return
     */
    @ApiOperation(value="数据字典查询接口", notes="用于后台管理系统中查询字典列表\n" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentCode", value = "父级code", required = false, dataType = "String"),
            @ApiImplicitParam(name = "dictCode", value = "字典code", required = false, dataType = "String"),
    })
    @ApiResponse(code = 0000,message = "成功",response = JsonModel.class)
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},value = "/dict/list")
    @ResponseBody
    public Object selectDictList(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value="parentCode",required =false) String parentCode,
                                 @RequestParam(value="dictCode",required =false) String dictCode,
                                 @RequestParam(value="keyword",required =false) String keyword,
                                 @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                 @RequestParam(value = "pageSize",required=false) Integer pageSize
                                 ){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> map = new HashMap<>(5);
        Page page = new Page();
        map.put("keyword",keyword);
        if(parentCode != null && ! parentCode.equals("")){
            map.put("parentCode",parentCode);
        }
        if(dictCode != null && ! dictCode.equals("")){
            map.put("dictCode",dictCode);
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
        JsonModel json = null;
        try {
            page = dictService.getDictList(page);
            json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
            json.setPageSize(pageSize);
            json.setPageCount(page.getPageCount());
            json.setTotal(page.getRowCount());
        } catch (Exception e){
            json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
        }
        return json;
    }

    /**
     * 字典表添加服务
     * @param request
     * @param response
     * @param nlpDict
     * 应用场景：用于添加服务
     * 逻辑：前端将需要添加的参数请求发送给后台，控制器在后台用对象nlpDict 接收，
     * 将请求发送到业务层处理业务，业务执行成功后将生成的子id：dictCode  返回前台
     * 结果：添加字典表成功。
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/dict/dictadd")
    @ResponseBody
    public Object dictToAdd(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody NlpDict nlpDict){
        response.setHeader("Access-Control-Allow-Origin", "*");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        nlpDict.setCreateTime(sdf.format(date));
        nlpDict.setUpdateTime(sdf.format(date));
        JsonModel json = null;
        try{
            int count = dictService.getDictToAdd(nlpDict);
            if (count > 0){
                json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
        }
        return json;
    }

    /**
     * 字典表修改服务
     * @param request
     * @param response
     * @param nlpDict
     * 应用场景：用于修改字典表服务
     * 逻辑：前端将要修改的参数请求发送到控制器，控制器在后台用对象nlpDict 接收，
     * 将请求发送到业务层处理业务，业务执行成功不需要向前台返回json。
     * 结果：修改字典表成功。
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/dict/updatedict")
    @ResponseBody
    public Object updateDict(HttpServletRequest request,HttpServletResponse response,
                             @RequestBody NlpDict nlpDict){
        response.setHeader("Access-Control-Allow-Origin", "*");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        nlpDict.setUpdateTime(sdf.format(date));
        try{
            dictService.updateDict(nlpDict);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            return json;
        }catch (Exception e){
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     * 字典树
     * 分页+关键字检索
     * @param dictCode
     * @param pageNow
     * @param pageSize
     * @return
     * @date 2018/6/4 15:25
     * last modified by JIT
     */ 
    @RequestMapping(value = "/dictTree/getDictListByPage",method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET})
    public JsonModel getDictTreeList(HttpServletResponse response,
                                     @RequestParam(value = "dictCode",required = false)String dictCode,
                                     @RequestParam(value = "keyword",required = false)String keyword,
                                     @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                     @RequestParam(value = "pageSize",required=false) Integer pageSize){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> map = new HashMap<>();
        Page page = new Page();
        map.put("dictCode",dictCode);
        map.put("keyword",keyword);
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
        try {
            page = dictService.getDictTreeList(page);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
            json.setPageSize(pageSize);
            json.setPageCount(page.getPageCount());
            json.setTotal(page.getRowCount());
            return json;
        } catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     * 物理删除，逻辑：删除之前先查询，看当前对象是否有子集，如果有子集，提示前台先删除子集。
     * @param dictCode
     * @return
     */
    @RequestMapping(value = "/dict/delDict",method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET})
    public JsonModel delDict(HttpServletResponse response,@RequestParam("dictCode")String dictCode){
        response.setHeader("Access-Control-Allow-Origin", "*");
        JsonModel json = null;
        int i = dictService.delDict(dictCode);
        if (i > 0){
            json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
        } else {
            json = new JsonModel(true,"删除失败，请先删除子集",ReturnCode.ERROR_CODE_11001.getKey(),null);
        }
        return json;
    }
}
