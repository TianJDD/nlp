package com.ultra.nlp.manage.service;

import com.ultra.nlp.manage.model.Classify;
import com.ultra.nlp.manage.model.NlpServiceManage;
import com.ultra.nlp.manage.model.Page;

import java.util.Map;

public interface IServiceService {
    Page getClassifyList(Page page);

    Page getClassifyTreeList(Page page);

    int addClassify(Classify classify);

    int delClassify(int id);

    int updateClassify(Classify classify) throws Exception;

    int addServiceManage(NlpServiceManage nlpServiceManage) throws Exception;

    int updateServiceManage(NlpServiceManage nlpServiceManage);

    int delService(int id);

    Page getServiceList(Page page);

    Map<String,Object> serviceDetail(Map<String,Object> map);
}
