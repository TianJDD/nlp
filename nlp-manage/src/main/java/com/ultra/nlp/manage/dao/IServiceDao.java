package com.ultra.nlp.manage.dao;

import com.ultra.nlp.manage.model.Classify;
import com.ultra.nlp.manage.model.NlpServiceManage;
import com.ultra.nlp.manage.model.NlpServiceRelate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface IServiceDao {
    int addServiceManage(NlpServiceManage nlpServiceManage);

    int addServiceManageRelate(Map<String,Object> serviceRelate);

    int updateServiceManage(NlpServiceManage nlpServiceManage);

    int delService(int id);

    List<Map<String,Object>> getServiceList(Map<String, Object> param);

    int getServiceListCounts(Map<String, Object> param);

    Map<String,Object> serviceDetail(Map<String,Object> map);

    List<Map<String,Object>> serviceDetailRelate(Map<String,Object> map);

    List<Map<String, Object>> getClassifyList(Map<String,Object> map);

    int getClassifyListCounts(Map<String,Object> map);

    int addClassify(Classify classify);

    int delClassify(int id);

    int updateClassify(Map<String,Object> map) throws Exception;

    Map<String,Object> classifyDetail(Map<String,Object> map);

    String getMaxSubId(String p_id);

    //弃用
    int updateServiceRelate(Map<String, Object> relate);

    int delServiceRelate(Map<String,Object> map);

    int addServiceRelate(Map<String, Object> relate);
}
