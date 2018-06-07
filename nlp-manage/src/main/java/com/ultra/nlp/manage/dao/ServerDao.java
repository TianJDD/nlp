package com.ultra.nlp.manage.dao;

import com.ultra.nlp.manage.model.NlpAccessInfo;
import com.ultra.nlp.manage.model.NlpServerManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface ServerDao {

    //服务录入信息
    int getServiceEntry(Map<String,Object> map) throws Exception;

    //录入端口和ip
    int addServerport(Map<String,Object> map)throws Exception;

    int updateServiceEntry(Map<String,Object> map)throws Exception;

    List<Map<String,Object>> getServerList (Map<String,Object> map) throws Exception;

    int getServerListCount(Map<String,Object> map)throws Exception;

    int updateServerInfo(Map<String,Object> map)throws Exception;

    Map<String,Object> serviceEntryInfo(Map<String,Object> map) throws Exception;

    int deleteServerPort(Map<String,Object> map) throws Exception;

}
