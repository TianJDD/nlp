package com.ultra.nlp.manage.controller;

import com.ultra.nlp.manage.model.*;
import com.ultra.nlp.manage.service.DataSetService;
import com.ultra.nlp.manage.service.IServerService;
import com.ultra.nlp.manage.service.IServiceService;
import com.ultra.nlp.manage.util.ExcelUtil;
import com.ultra.nlp.manage.util.FormatUtil;
import com.ultra.nlp.manage.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
@CrossOrigin
@Controller
@RequestMapping(value="/classify")
public class ServiceController {

    private final static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    DataSetService dataSetService;

    @Autowired
    IServiceService serviceService;

    @Autowired
    IServerService entryService;
    /**
     * 查看分类列表
     * 不传id，则查看全部，传id则查看其子级服务列表
     * @param request
     * @param response
     * @param id
     * @return
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},value = "/typeTreeList")
    @ResponseBody
    public Object classifyTreeList(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "id",required=false) String id,
                                 @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                 @RequestParam(value = "pageSize",required=false) Integer pageSize
    ){
        Map map = new HashMap<String,Object>();
        Page page = new Page();
        if (null != id){
            map = new HashMap<String,String>();
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
        try{
            logger.info("传入参数id为：" + id + ",pageNow为：" + pageNow + ",pageSize为：" + pageSize);
            page = serviceService.getClassifyTreeList(page);
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
     * 查看分类列表
     * 不传id，则查看全部，传id则查看其子级服务列表
     * @param request
     * @param response
     * @param id
     * @return
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},value = "/typeList")
    @ResponseBody
    public Object classifyList(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "id",required=false) String id,
                                  @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                  @RequestParam(value = "pageSize",required=false) Integer pageSize
    ){
        Map map = new HashMap<String,Object>();
        Page page = new Page();
        if (null != id){
            map = new HashMap<String,String>();
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
        try{
            logger.info("传入参数id为：" + id + ",pageNow为：" + pageNow + ",pageSize为：" + pageSize);
            page = serviceService.getClassifyList(page);
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
     * 添加分类
     * @param request
     * @param response
     * @param name
     * @param descr
     * @param icon
     * @param rec_num
     * @param img
     * @param leaf
     * @param p_id
     * @param ser_type
     * @return
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET},value = "/typeAdd")
    @ResponseBody
    public Object addClassify(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "name",required=false) String name,
                             @RequestParam(value = "descr",required=false) String descr,
                             @RequestParam(value = "icon",required=false) String icon,
                             @RequestParam(value = "rec_num",required=false) String rec_num,
                             @RequestParam(value = "img",required=false) String img,
                             @RequestParam(value = "leaf",required=false) String leaf,
                             @RequestParam(value = "p_id",required=false) String p_id,
                             @RequestParam(value = "ser_type",required=false) String ser_type
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Classify classify = new Classify();
        classify.setName(name);
        classify.setDescr(descr);
        //classify.setIcon(icon);
        if(null != rec_num && ! "".equals(rec_num)){
            classify.setRec_num(new BigDecimal(Double.parseDouble(rec_num)));
        }
        classify.setImg(img);
        if(null != leaf && ! "".equals(leaf)){
            classify.setLeaf(Integer.parseInt(leaf));
        }
        classify.setP_id(p_id);
        if(null != ser_type && ! "".equals(ser_type)){
            classify.setSer_type(Integer.parseInt(ser_type));
        }
        classify.setExt1("01");
        try{
            serviceService.addClassify(classify);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            return json;
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     * 修改分类
     * @param request
     * @param response
     * @param classify
     * @return
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET},value ="/typeUpdate")
    @ResponseBody
    public Object updateClassify(HttpServletRequest request, HttpServletResponse response,@RequestBody Classify classify){
        response.setHeader("Access-Control-Allow-Origin", "*");
        logger.info("*****************修改分类方法入参Classify ："+ classify.toString());
        try{
            if(classify.getId() == null || "".equals(classify.getId())){
                JsonModel json = new JsonModel(true,"id不能为空",ReturnCode.ERROR_CODE_11001.getKey(),null);
                return json;
            }
            serviceService.updateClassify( classify);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            return json;
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     * 删除分类（逻辑删）
     * ext1 设置为状态字段：01 启用 00 删除
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},value = "/typeDel")
    @ResponseBody
    public Object delClassify(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "id",required=true) String id){
        response.setHeader("Access-Control-Allow-Origin", "*");
        try{
            serviceService.delClassify(Integer.parseInt(id));
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
            return json;
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }

    }

    /**
     * 获取最下层分类下的具体服务列表
     * 返回前台结果为map类型
     * @param request
     * @param response
     * @param id
     * @param keyword
     * @param pageNow
     * @param pageSize
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},value = "/serviceList")
    @ResponseBody
    public Object getServiceList(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "id") Integer id,
                                 @RequestParam(value = "keyword",required = false)String keyword,
                                 @RequestParam(value = "pageNow",required=false) Integer pageNow,
                                 @RequestParam(value = "pageSize",required=false) Integer pageSize
    ){
        Map map = new HashMap<String,Object>();
        Page page = new Page();
        map.put("keyword",keyword);
        if (null != id){
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
        try{
            logger.info("传入参数id为：" + id + "keyword为：" +  keyword + ",pageNow为：" + pageNow + ",pageSize为：" + pageSize);
            page = serviceService.getServiceList(page);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultMap());
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
     * 修改服务
     * 场景：修改服务
     * 逻辑：前台传过来的nlpServiceManage中有NLPServiceRelate集合，判断集合的size进行对nlp_service_relate的修改，
     * 修改逻辑为先通过serviceId进行删除，再添加。
     * 需要入参：nlpServiceManage（必须带id），
     * 其中nlpServiceRelate中应有的参数（serviceId == nlpServiceManage的id, relateType, title, content, banner）
     * @date 2018/5/15 10:47
     * last modified by JIT
     * @param request
     * @param response
     * @param nlpServiceManage
     * @return
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/updateService")
    @ResponseBody
    public Object updateService(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody NlpServiceManage nlpServiceManage){
        try{
            logger.info("传入参数为："+ nlpServiceManage.toString());
            String updateTime = FormatUtil.DateFormat();
            nlpServiceManage.setUpdateTime(updateTime);
            int n  =serviceService.updateServiceManage(nlpServiceManage);
            JsonModel json = null;
            if(n > 0) {
                 json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(), nlpServiceManage);
            }else {
                json = new JsonModel(true, "修改服务失败！", ReturnCode.ERROR_CODE_11001.getKey(), nlpServiceManage);
            }
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }


    /**
     * 添加服务 接口
     * 1、场景：用于管理平台维护服务信息
     * 2、传入参数：
     * @param nlpServiceManage 服务信息和服务相关信息
     * 3、逻辑：保存服务主信息和服务相关信息
     * 4、结果：返回保存成功/失败信息
     * 修改：增加创建时间和修改时间，新增时默认设置修改时间就是创建时间。
     * @param request
     * @param response
     * @return
     * @date 2018/5/15 11:57
     * last modified by JIT
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/addService")
    @ResponseBody
    public Object addService(HttpServletRequest request, HttpServletResponse response,
                             @RequestBody NlpServiceManage nlpServiceManage){
        try{
            logger.info("传入参数为："+ nlpServiceManage.toString());
            int n  =serviceService.addServiceManage(nlpServiceManage);
            if(n > 0) {
                JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(), nlpServiceManage);
                return json;
            }else {
                JsonModel json = new JsonModel(true, "新增服务失败！", ReturnCode.ERROR_CODE_11001.getKey(), nlpServiceManage);
                return json;
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     * 逻辑删除服务，只把数据库中的状态更改，不做物理删除
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},value = "/delService")
    @ResponseBody
    public Object delService(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "id",required=true) String id){
        response.setHeader("Access-Control-Allow-Origin", "*");
        try{
            int i = serviceService.delService(Integer.parseInt(id));
            if (i > 0){
                JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),null);
                return json;
            } else {
                JsonModel json = new JsonModel(true,"修改服务失败",ReturnCode.ERROR_CODE_11001.getKey(),null);
                return json;
            }
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(true,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }

    }

    /**
     * 功能描述: 查询服务详情接口-用于管理后台编辑服务信息前展示
     *根据服务id查询服务详情，包括服务主表信息和相关表信息
     * last modified by JIT on 2018年5月9日10:02:58
     * @param: id 服务id
     * @return:
     * @auther: guyuefei
     * @date:
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/serviceDetail")
    @ResponseBody
    public Object serviceDetail(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam (value = "serviceId",required=true) String serviceId){
        try{
            logger.info("传入i的服务id为："+ serviceId);
            Map<String,Object> map = new HashMap<>(10);
            map.put("id",serviceId);
            map  = serviceService.serviceDetail(map);
            Map<String,Object> para = new HashMap<>();
            para.put("serviceId",serviceId);
            para.put("accessType",map.get("accessType"));
            para = entryService.serviceEntryInfo(para);
            if(para != null && ! para.isEmpty()){
                map.put("hasServerEntry",true);
            }else{
                map.put("hasServerEntry",false);
            }
            map.remove("id");
            JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(), map);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     * 功能描述:
     * 生成服务信息导入模板下载
     * @param:
     * @return:
     * @auther: guyuefei
     * @date:
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/service/download/templet")
    @ResponseBody
    public Object downloadServiceTemplet(HttpServletRequest request, HttpServletResponse response){

        String fileName ="接入服务信息录入模板" + Calendar.getInstance().getTimeInMillis();
        OutputStream os = null;
        try {
            List<String> cells = new ArrayList<>();
            cells.add("所属分类大类");
            cells.add("所属分类小类");
            cells.add("服务名称");
            cells.add("服务描述");
            cells.add("调用路径");
            cells.add("输入参数");
            cells.add("所属学校");
            cells.add("所有者");
            cells.add("属性");
            cells.add("服务类型");
            cells.add("开发方式");
            cells.add("项目类型");
            cells.add("访问地址");
            cells.add("联系人");
            cells.add("联系人电话");
            cells.add("泰岳对接人");
            XSSFWorkbook workbook = (XSSFWorkbook)ExcelUtil.ExcelTemplet(cells);
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
            os= new BufferedOutputStream(response.getOutputStream());
            workbook.write(os);

            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }finally{
            try {
                if(null != os){
                    os.close();
                }
            } catch (IOException e2) {
                logger.error(e2.getMessage());
            }

        }
        JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(), fileName);
        return json;
    }

    /**
     * 功能描述:
     * 生成服务信息模板下载
     * @param: url
     * @return:
     * @auther:  zy
     * @date: 2018/5/14
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/export/load/down")
    public void ExpotDown(HttpServletResponse response,String url)  {

        String fileName ="接入服务信息模板" + Calendar.getInstance().getTimeInMillis();
        //ExcelUtil.subFilePath(url);
        ExcelUtil.subFilePath(this.getClass().getResource("").toString());
        try{
            File file = new File(url);
            InputStream in = new FileInputStream(file);
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int length;
            while ((length = in.read(b)) > 0){
                os.write(b,0,length);
            }
            in.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 功能描述:
     * 服务接入信息录入
     * @param:
     * @return:
     * @auther: guyuefei
     * @date:
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/service/serviceEntry/add")
    @ResponseBody
    public Object addServiceEntry(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "serverId",required = false)String serverId,
                                  @RequestParam(value = "accessId",required = false)String accessId,
                                  @RequestParam(value = "accessType",required = false)String accessType,
                                  @RequestParam(value = "accessModel",required = false)String accessModel,
                                  @RequestParam(value = "deployPath",required = false)String deployPath,
                                  @RequestParam(value = "visitURL",required = false)String visitURL,
                                  @RequestParam(value = "academyId",required = false)String academyId,
                                  @RequestParam(value = "professorId",required = false)String professorId,
                                  @RequestParam(value = "accessWay",required = false)String accessWay,
                                  @RequestParam(value = "accessState",required = false)String accessState,
                                  @RequestParam(value = "contactPerson",required = false)String contactPerson,
                                  @RequestParam(value = "contactTel",required = false)String contactTel,
                                  @RequestParam(value = "accessPerson",required = false)String accessPerson){
        Map<String,Object> map = new HashMap<>(14);

        try{
            Map<String,Object> para = new HashMap<>();
            para.put("id",accessId);
            para = serviceService.serviceDetail(para);
            if(null != serverId && !"".equals(serverId) && !"null".equals(serverId)){
                map.put("serverId",serverId);
            }
            if(null != accessId && !"".equals(accessId) && !"null".equals(accessId)){
                map.put("accessId",accessId);
            }
            map.put("id",StringUtil.getUUID());
            map.put("accessType",para.get("accessType"));
            map.put("accessModel",accessModel);
            map.put("deployPath",deployPath);
            map.put("visitURL",visitURL);
            if(null != academyId && !"".equals(academyId) && !"null".equals(academyId)){
                map.put("academyId",academyId);
            }
            map.put("academyId",academyId);
            map.put("professorId",professorId);
            map.put("accessWay",accessWay);
            map.put("accessState",accessState);
            map.put("contactPerson",contactPerson);
            map.put("contactTel",contactTel);
            map.put("accessPerson",accessPerson);
            logger.info("传入参数为："+ map.toString());
            map =entryService.getServiceEntry(map);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),map);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     * 功能描述: 查询接入服务信息详情接口
     * 用于管理平台维护服务详情页维护接入信息前信息展示
     *根据服务id或者接入服务信息详情id查询接入服务信息
     * @param: serviceId 服务id 或者解决方案id
     * @param ：id 服务接入信息表id
     * @return: 接入服务信息详情
     * @auther: guyuefei
     * @date:
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/service/serviceEntryInfo")
    @ResponseBody
    public Object serviceEntryInfo(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam (value = "serviceId",required=false) String serviceId,
                                   @RequestParam (value = "id",required=false) String id){
        try{
            logger.info("传入的服务id为："+ serviceId + "接入信息表id为：" + id);
            Map<String,Object> map = new HashMap<>(10);
            if((null == serviceId || "".equals(serviceId)) && (null == id || "".equals(id))){
                JsonModel json = new JsonModel(true, "serviceId 或者 id 不能为空", ReturnCode.ERROR_CODE_11001.getKey(), null);
                return json;
            }
            Map<String,Object> para = new HashMap<>();
            para.put("id",serviceId);
            para = serviceService.serviceDetail(para);
            if(null != serviceId && !"" .equals(serviceId) && !"null" .equals(serviceId)){
                map.put("serviceId",serviceId);
                map.put("accessType",para.get("accessType"));
            }
            if(null != id && !"".equals(id)  && !"null".equals(id)){
                map.put("id",id);
            }
            map  = entryService.serviceEntryInfo(map);
            JsonModel json = new JsonModel(true, ReturnCode.SUCESS_CODE_0000.getValue(), ReturnCode.SUCESS_CODE_0000.getKey(), map);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }
}
