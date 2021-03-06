package com.ultra.nlp.manage.controller;

import com.ultra.nlp.manage.model.JsonModel;
import com.ultra.nlp.manage.model.Page;
import com.ultra.nlp.manage.model.ReturnCode;
import com.ultra.nlp.manage.service.FileuploadService;
import com.ultra.nlp.manage.util.FileUploadUtil;
import com.ultra.nlp.manage.util.FormatUtil;
import com.ultra.nlp.manage.util.StringUtil;
import com.ultra.nlp.manage.util.UnicodeUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 功能描述:
 *  上传文件
 * @param:
 * @return:
 * @auther: guyuefei
 * @date:
 */
@CrossOrigin
@RestController
@RequestMapping("/manage/file")
public class FileUploadController {
    private final static Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    FileuploadService fileuploadService;


    /**
     * 功能描述:
     * 通过ftp上传文件
     * @param:
     * @return:
     * @auther: guyuefei
     * @date:
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET},value = "ftp/upload")
    @ResponseBody
    public Object ftpAdd(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "url",required=false) String url,//FTP服务器hostname
                                     @RequestParam(value = "port",required=false) int port,//FTP服务器端口
                                     @RequestParam(value = "username",required=false) String username, // FTP登录账号
                                     @RequestParam(value = "password",required=false) String password, //FTP登录密码
                                     @RequestParam(value = "path",required=false)String path, //FTP服务器保存目录
                                     @RequestBody MultipartFile uploadFile//上传到FTP服务器上的文件名
    ) {
        try {
            InputStream input = null;
            String filename = uploadFile.getOriginalFilename();
            input = uploadFile.getInputStream();
            boolean flag = FileUploadUtil.uploadFile(url,port,username,password,path,filename,input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用MultipartFile上传文件
     * @param request
     * @param response
     * @param uploadFile
     * @param fileType 上传文件类型
     * @param fileDesc 上传文件描述
     * @return
     * @throws IOException
     */
    @ApiOperation(value="用MultipartFile上传文件", notes="用于后台管理系统中上传图片\n" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileType", value = "图片类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fileDesc", value = "图片描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "uploadFile", value = "上传文件对应参数名", required = true, dataType = "String")
    })
    @ApiResponse(code = 0000,message = "成功",response = JsonModel.class)
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET},value = "/multi/upload")
    @ResponseBody
    public Object multiAdd(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "fileType", required = false) MultipartFile fileType,
                           @RequestParam(value = "fileDesc", required = false) MultipartFile fileDesc,
                           @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile
    ) throws IOException {

        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        String newName = null;
        try{
            //获取服务器ip
            String address = InetAddress.getLocalHost().getHostAddress().toString();
            String filename = uploadFile.getOriginalFilename();
            inputStream = uploadFile.getInputStream();
            long now = System.currentTimeMillis();
            //String newName = now  + (filename.substring(filename.lastIndexOf("."),filename.length()));
            newName = now +"_"+ UnicodeUtil.decodeUnicode(filename);
            File file = new File("/var/local/nlp_static", now +"_"+ UnicodeUtil.decodeUnicode(filename));
            file.createNewFile();
            outputStream = new FileOutputStream(file);
            byte temp[] = new byte[1024];
            int size = -1;
            while ((size = inputStream.read(temp)) != -1) { // 每次读取1KB，直至读完
                outputStream.write(temp, 0, size);
            }
            logger.info("File load success.");
        }catch(IOException e){
            e.printStackTrace();
        } finally {
            if(outputStream != null)
                outputStream.close();
            if(inputStream != null)
                inputStream.close();
        }
        newName = "http://www.china-nlp.com/nlp_static/" + newName;
        JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(), newName);
        return json;
    }

    /**
     * 查询图片文件列表
     * 用途：用于所有查询图片文件列表
     * @author guyuefei
     * @param request
     * @param response
     * @param fileDesc 文件名/描述 可模糊查询
     * @param fileType 文件类型
     * @return
     */
    @ApiOperation(value=" 查询图片文件列表", notes="用于后台管理系统中图片列表展示\n" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileType", value = "图片类型", required = false, dataType = "String"),
            @ApiImplicitParam(name = "fileDesc", value = "图片描述", required = false, dataType = "String"),
            @ApiImplicitParam(name = "pageNow", value = "分页当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页码大小", required = true, dataType = "Integer")
    })
    @ApiResponse(code = 0000,message = "成功",response = JsonModel.class)
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET},value = "/list")
    @ResponseBody
    public Object imgFileList(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "fileDesc", required = false) String fileDesc,
                             @RequestParam(value = "fileType", required = false) String fileType,
                             @RequestParam(value = "pageNow", required = false) Integer pageNow,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize
    ){
        Map<String,Object> map = new HashMap<>(5);
        try{
            if(! StringUtil.empty(fileType))
                map.put("dictCode",fileType);
            if(! StringUtil.empty(fileDesc))
                map.put("imageName",fileDesc);
            Page page = new Page(pageNow, pageSize);
            if (pageNow != null && pageSize != null) {
                map.put("queryStart", page.getQueryStart());
                map.put("pageSize", page.getPageSize());
            }
            page.setParam(map);
            page = fileuploadService.getImgFileList(page);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),page.getResultList());
            json.setPageSize(pageSize);
            json.setPageCount(page.getPageCount());
            json.setTotal(page.getRowCount());
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     * 查询图片文件详情
     * 用途：用于所有查询图片详情
     * @author guyuefei
     * @param request
     * @param response
     * @param imageId 文件名主键id
     * @return
     */
    @ApiOperation(value="查询图片文件详情", notes="用于后台管理系统中查看图片\n" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageId", value = "图片id", required = true, dataType = "String"),
    })
    @ApiResponse(code = 0000,message = "成功",response = JsonModel.class)
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET},value = "/imgdetail")
    @ResponseBody
    public Object imgdetail(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "imageId", required = false) String imageId
    ){
        Map<String,Object> map = new HashMap<>(1);
        try{
            map.put("id",imageId);
            map = fileuploadService.selectImgFileDetail(map);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),map);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }
    /**
     * 保存文件路径
     * 用途：用于管理系统上传图片等文件存储路径到数据库
     * @author guyuefei
     * @param request
     * @param response
     * @param fileDesc 文件名/描述
     * @param fileType 文件类型
     * @param fileUrl 文件存储路径
     * @return
     * @throws IOException
     */
    @ApiOperation(value="保存文件路径到数据库", notes="用于后台管理系统中图片路径\n" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileType", value = "图片类型,类型值是从字典表中取出", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fileDesc", value = "图片描述", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fileUrl", value = "图片url", required = true, dataType = "String"),
    })
    @ApiResponse(code = 0000,message = "成功",response = JsonModel.class)
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET},value = "/addImgFile")
    @ResponseBody
    public Object addImgFile(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "fileDesc", required = false) String fileDesc,
                              @RequestParam(value = "fileType", required = false) String fileType,
                              @RequestParam(value = "fileUrl", required = false) String fileUrl
    ){
        Map<String,Object> map = new HashMap<>(5);
        try{
            String imageId = StringUtil.getUUID();
            map.put("dictCode",fileType);
            map.put("imageName",fileDesc);
            map.put("imageUrl",fileUrl);
            map.put("id",imageId);
            map.put("createTime",FormatUtil.getLongTime());
            int n = fileuploadService.addImgFile(map);
            if(n < 1){
                JsonModel json = new JsonModel(false,"新增数据失败",ReturnCode.ERROR_CODE_11001.getKey(),map);
                return json;
            }
            map.clear();
            map.put("imageId",imageId);
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),map);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     * 删除数据库中图片表的数据
     * 用途：用于管理系统对图片的维护-删除操作
     * @param request
     * @param response
     * @param imageId
     * @return
     */
    @ApiOperation(value=" 删除数据库中图片表的数据", notes="用于后台管理系统中删除图片路径\n" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageId", value = "图片id", required = true, dataType = "String"),
    })
    @ApiResponse(code = 0000,message = "成功",response = JsonModel.class)
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET},value = "/deleteImg")
    @ResponseBody
    public Object deleteImg(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "imageId", required = false) String imageId
    ){
        Map<String,Object> map = new HashMap<>(1);
        try{
            map.put("id",imageId);
            int n  = fileuploadService.deleteImgFile(map);
            if(n < 1){
                JsonModel json = new JsonModel(true,"删除0条数据",ReturnCode.SUCESS_CODE_0000.getKey(),map);
                return json;
            }
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),map);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }
    /**
     * 修改文件存储信息
     * 用途：用于管理系统修改图片等文件数据库中的存储信息
     * @author guyuefei
     * @param request
     * @param response
     * @param imageId 主键id
     * @param fileDesc 文件名/描述
     * @param fileType 文件类型
     * @param fileUrl 文件存储路径
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET},value = "/updateImgFile")
    @ResponseBody
    public Object updateImgFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "imageId", required = false) String imageId,
                           @RequestParam(value = "fileDesc", required = false) String fileDesc,
                           @RequestParam(value = "fileType", required = false) String fileType,
                           @RequestParam(value = "fileUrl", required = false) String fileUrl
    ){
        Map<String,Object> map = new HashMap<>(5);
        try{
            map.put("dictCode",fileType);
            map.put("imageName",fileDesc);
            map.put("imageUrl",fileUrl);
            map.put("id",imageId);
            map.put("createTime",FormatUtil.getLongTime());
            int n = fileuploadService.updateImgFile(map);
            if(n < 1){
                JsonModel json = new JsonModel(true,"更新0条数据",ReturnCode.SUCESS_CODE_0000.getKey(),map);
                return json;
            }
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),map);
            return json;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            JsonModel json = new JsonModel(false,ReturnCode.ERROR_CODE_11001.getValue(),ReturnCode.ERROR_CODE_11001.getKey(),null);
            return json;
        }
    }

    /**
     *  删除服务器上的文件
     * @param request
     * @param response
     * @param fileUrl
     * @return
     * @throws IOException
     */
    @ApiOperation(value=" 删除服务器上的文件", notes="用于后台管理系统中删除服务器上的图片\n" )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileUrl", value = "图片id", required = true, dataType = "String"),
    })
    @ApiResponse(code = 0000,message = "成功",response = JsonModel.class)
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET},value = "/delete")
    @ResponseBody
    public Object deleteFile(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "fileUrl", required = false) String fileUrl
    ) throws IOException {
        File file = new File(fileUrl);
        if(file.isFile() && file.exists()){
            file.delete();
            if(file.exists()){
                JsonModel json = new JsonModel(false,"删除失败",ReturnCode.ERROR_CODE_11001.getKey(),null);
                return json;
            }
        }else{
            JsonModel json = new JsonModel(true,"未找到文件",ReturnCode.SUCESS_CODE_0000.getKey(),null);
            return json;
        }
        return null;
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @param url
     * @param fileName
     * @param method
     * @throws IOException
     */
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST, RequestMethod.GET},value = "/download")
    @ResponseBody
    public void downloadFile(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "url", required = false) String url,
                               @RequestParam(value = "fileName", required = false) String fileName,
                               //@RequestParam(value = "filePath", required = false) String filePath,
                               @RequestParam(value = "method", required = false) String method
    ) throws IOException {
        /*File file=new File("192.168.95.57/var/local/static"+fileName);
        //判断文件夹是否存在
        if (!file.exists())
        {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }*/
        //FileOutputStream fileOut = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        OutputStream bos = null;
        BufferedInputStream bis = null;
        try
        {
            // 建立链接
            URL httpUrl=new URL(url);
            conn=(HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // post方式不能使用缓存
            conn.setUseCaches(false);
            //连接指定的资源
            conn.connect();
            //获取网络输入流
            inputStream=conn.getInputStream();
            bis = new BufferedInputStream(inputStream);
            bos =  response.getOutputStream();
            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while(length != -1)
            {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.flush();
            bos.close();
            bis.close();
            conn.disconnect();
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }finally {
            if(bos != null){
                bos.close();
            }
            if(bis != null){
                bis.close();
            }
        }

        //return file;
    }

    public static void main(String[] args){
        String filename = "asas.ass.jpg";
        System.out.println(filename.substring(filename.lastIndexOf("."),filename.length()));
    }
}
