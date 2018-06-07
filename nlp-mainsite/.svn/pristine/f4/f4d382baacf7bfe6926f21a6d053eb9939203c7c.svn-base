package com.ultra.nlp.mainsite.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ultra.nlp.mainsite.RestController.ExecuteServiceContrioller;
import com.ultra.nlp.mainsite.dao.IServiceDao;
import com.ultra.nlp.mainsite.model.JsonModel;
import com.ultra.nlp.mainsite.model.Page;
import com.ultra.nlp.mainsite.model.ReturnCode;
import com.ultra.nlp.mainsite.model.ServiceDetail;
import com.ultra.nlp.mainsite.service.IExcuteService;
import com.ultra.nlp.mainsite.service.IServiceService;
import com.ultra.nlp.mainsite.util.UnicodeUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service(value="excuteService")
@Transactional
public class ExcuteServiceImpl implements IExcuteService {
    private final static Logger logger = LoggerFactory.getLogger(ExcuteServiceImpl.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    IServiceService serviceService;


    /**
     * 功能描述:
     * httpclient方式调用Rest类型的服务
     * @param:
     * @return:
     * @auther: guyuefei
     * @date:
     */
    @Override
    public String HttpClientPostWithJSON(String username, String userId, String serviceId, String type, String url, String param) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        String respContent = null;
        StringEntity entity = new StringEntity(param,"utf-8");//解决中文乱码问题    
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        HttpResponse resp = client.execute(httpPost);
        logger.info("接收到的全部参数为：" + resp);
        Map map = new HashMap(10);
        map.put("userName",username);
        map.put("userId",userId);
        map.put("fromType",type);
        map.put("serviceId",serviceId);
        map.put("dateTime",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        String str=null;
        if(resp.getStatusLine().getStatusCode() == 200) {
            org.apache.http.HttpEntity entity1 = resp.getEntity();
            respContent = EntityUtils.toString(entity1,"UTF-8");
            str= UnicodeUtil.decodeUnicode(respContent);
            logger.info("接收到的参数解码后为：" + str);
            /**
             * 取出data
             */
            try{
                Map data = (Map)JSON.parse(str);
                if(data.get("data") != null){
                    str = data.get("data") + "";
                }
                logger.info("取出data的参数为：" + str);
            }catch(Exception e){
                logger.error(e.getMessage(),e);
            }
            //logger.info("解码后返回前端的参数为：" + str);
            map.put("type",1);//更新成功的使用次数
            int n = serviceService.updateServiceUseCount(map);
        }else{
            map.put("type",2);//更新失败的使用次数
            int n = serviceService.updateServiceUseCount(map);

        }
        map = null;
        return str;
    }
    /**
     * RestTemplete方式调用Rest类型的服务
     * @param username
     * @param userId
     * @param serviceId
     * @param type
     * @param url
     * @param param
     * @return
     */
    @Override
    public JsonModel RestService(String username, String userId, String serviceId, String type, String url, String param) {
        Map map = new HashMap(1);
        /*map.put("dictCode",type);
        map = serviceService.getServiceIpAndPort(map);*/
        //添加post请求头，如果不加会报415错误
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(mediaType);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(param, headers);

        //调用本地代码进行测试
        //String URL = "http://10.10.24.79:9101/nlpapi" + url;
        try{
            String res = this.restTemplate.postForEntity(url , formEntity, String.class).getBody();
            logger.info("调用服务url为：" + url + ",传入参数为：" + param + "。接收到的参数为：" + res);
            //将返回参数改为json格式
            JSONObject returnJson = JSONObject.parseObject(res);
            //更新用户使用服务表中的使用次数
            map.clear();
            map.put("userName",username);
            map.put("userId",userId);
            map.put("fromType",type);
            map.put("serviceId",serviceId);
            map.put("dateTime",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            if(!(boolean)returnJson.get("notSuccess") && (boolean)returnJson.get("success")){

                map.put("type",1);//更新成功的使用次数
                int n = serviceService.updateServiceUseCount(map);
                map.clear();
                map.put("status",returnJson.get("status"));
                map.put("context",returnJson.get("context"));
                map.put("returnData",returnJson.get("data"));
                JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),map);
                return json;
            }else{
                map.put("type",2);//更新失败的使用次数
                int n = serviceService.updateServiceUseCount(map);
                map.clear();
                map.put("status",returnJson.get("status"));
                map.put("context",returnJson.get("context"));
                map.put("err",returnJson.get("err"));
                map.put("returnData","");
                JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),map);
                return json;
            }
        }catch(Exception e){
            logger.info(e.getLocalizedMessage(),e);
            map.clear();
            map.put("status","error");
            map.put("returnData","服务暂时不可用，请联系我们......");
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),map);
            return json;
        }
    }

    @Override
    public void excuteWebsocket(String url, String param,String message) throws Exception {
        WebSocketClient client = (WebSocketClient)this.websocket(url,param);
        //因为websocket是全双工通信方式，所以需要等待返回结果返回
        boolean flag = true;
        while(flag){
            if(ExecuteServiceContrioller.getMessage() != null && !client.isClosed()){
                client.close();
                //if(!client.isClosed()){
                //logger.info("链接未关闭");
                //}
            }
            if(client.isClosed()){
                logger.info("message 是：" + message + "，链接关闭了");
                flag = false;
            }
        }
        client = null;
        return ;
    }
    /**
     * 连接websocket接口
     * 苏州大学 接入服务
     * @return
     */
    @Override
    public Object websocket(String url, String param) throws URISyntaxException, NotYetConnectedException, IOException {
        //WebSocketClient的超时时间是毫秒值
        WebSocketClient  client = new WebSocketClient(new URI(url),new Draft_6455(),null,90000) {

            @Override
            public void onOpen(ServerHandshake arg0) {
                ExecuteServiceContrioller.setMessageNull();
                logger.info("链接已打开");
            }

            @Override
            public void onMessage(String arg0) {
                logger.info("收到消息"+arg0);
                ExecuteServiceContrioller.setMessage(arg0);
            }

            @Override
            public void onError(Exception arg0) {
                arg0.printStackTrace();
                logger.info("发生错误已关闭");
            }

            @Override
            public void onClose(int arg0, String arg1, boolean arg2) {
                ExecuteServiceContrioller.outMessage();
                logger.info("链接已关闭");
            }

            @Override
            public void onMessage(ByteBuffer bytes) {
                try {
                    System.out.println(new String(bytes.array(),"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        };

        client.connect();
        while(!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)){
            logger.info("尝试打开连接");
        }
        client.send(param);
        return client;
    }
}

