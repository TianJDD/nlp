package com.ultra.nlp.mainsite.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ultra.nlp.mainsite.model.JsonModel;
import com.ultra.nlp.mainsite.model.ReturnCode;
import com.ultra.nlp.mainsite.service.IExcuteService;
import com.ultra.nlp.mainsite.service.IServiceService;
import com.ultra.nlp.mainsite.util.FormatUtil;
import com.ultra.nlp.mainsite.util.UnicodeUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.groovy.util.StringUtil;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用接入服务的接口
 */
@CrossOrigin
@Controller
@RequestMapping(value = "/execute")
public class ExecuteServiceContrioller {
    private final static Logger logger = LoggerFactory.getLogger(ExecuteServiceContrioller.class);

    private static String  message;
    private static JsonModel json;
   @Autowired
    RestTemplate restTemplate;

   @Autowired
   IServiceService serviceService;

   @Autowired
   IExcuteService excuteService;

    /**
     * 执行调用服务接口
     * @param request
     * @param response
     * @param param 调用服务所需参数
     * @return
     */
    @RequestMapping(method = {RequestMethod.OPTIONS,RequestMethod.POST,RequestMethod.GET},
            value = "/service")
    @ResponseBody
    @Transactional
    public Object ExecuteService(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "username",required=false) String username,
                                 @RequestParam(value = "userId",required=false) String userId,
                                 @RequestParam(value = "serviceId",required=false) String serviceId,
                                 @RequestParam(value = "type",required=false) String type,
                                 @RequestParam(value = "url",required=false) String url,
                                 @RequestParam(value = "param",required=false) String param
        ){
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map map = new HashMap(5);
        try{
            logger.info("调用服务类型为："+ type +"调用服务url为：" + url + ",传入参数为：" + param );
            if(type.equals("001001")){//调用Rest服务
               // return this.RestService(username,userId,serviceId,type,url,param);
                JSONObject jsonObj = JSONObject.parseObject(param);
                logger.info("json转换参数为：" + jsonObj );
                long time1 = FormatUtil.getTimeLong();
                String resp =  excuteService.HttpClientPostWithJSON(username,userId,
                        serviceId, type,url,jsonObj.toString());
                //JSONObject returnJson = JSONObject.parseObject(resp);
                long time2 = FormatUtil.getTimeLong();
                logger.info("调用服务耗时为：" + (time2 - time1 ) + "ms");
                if(resp == null){
                    resp = "服务暂时不可用，请联系我们。。。。。。";
                    //map.put("data","服务暂时不可用，请联系我们。。。。。。");
                    //returnJson = JSONObject.parseObject(JSON.toJSONString(map));
                }
                JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),resp);
                return json;
            }
            if(type.equals("001002")){//调用websocket服务
                int count = 0;
                message = null;
                while(message == null){
                    excuteService.excuteWebsocket(url,param,message);
                    count ++;
                }
                logger.info("一共执行websocket调用了："+ count +"次");
                //message = message.replaceAll("[^0-9a-zA-Z]","");
                map.put("data",message);
                logger.info("返回message ：" + message);
                JSONObject returnJson = JSONObject.parseObject(JSON.toJSONString(map));
                JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),returnJson);
                return json;
            }

            return null;
        }catch(Exception e){
            map.clear();
            logger.info(e.getLocalizedMessage(),e);
            map.put("status","error");
            map.put("data","服务暂时不可用，请联系我们......");
            JSONObject returnJson = JSONObject.parseObject(JSON.toJSONString(map));
            JsonModel json = new JsonModel(true,ReturnCode.SUCESS_CODE_0000.getValue(),ReturnCode.SUCESS_CODE_0000.getKey(),returnJson);
            return json;
        }
    }


    public static synchronized void setMessage(String object){
        logger.info("收到message赋值：" + object);
        message = object;
    }

    public static synchronized void setMessageNull(){
        message = null;
    }
    public static synchronized String getMessage(){
        return message;
    }
    public static synchronized void outMessage(){
        logger.info("message输出打印：" + message);
    }
}
