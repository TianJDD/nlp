package com.ultra.nlp.mainsite.service;


import com.ultra.nlp.mainsite.model.JsonModel;
import com.ultra.nlp.mainsite.model.Page;
import com.ultra.nlp.mainsite.model.ServiceDetail;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.channels.NotYetConnectedException;
import java.util.List;
import java.util.Map;

public interface IExcuteService {
    /**
     * 功能描述:
     * httpclient方式调用Rest类型的服务
     * @param:
     * @return:
     * @auther: guyuefei
     * @date:
     */
    public String HttpClientPostWithJSON(String username,String userId ,String serviceId,String type,String url,String param) throws Exception;
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
    public JsonModel RestService(String username, String userId , String serviceId, String type, String url, String param);

    public void excuteWebsocket(String url,String param,String message) throws Exception;
    /**
     * 连接websocket接口
     * 苏州大学 接入服务
     * @return
     */
    public Object websocket(String url,String param ) throws URISyntaxException, NotYetConnectedException, IOException;


}
