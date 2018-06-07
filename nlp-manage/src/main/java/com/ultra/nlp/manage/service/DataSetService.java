package com.ultra.nlp.manage.service;

import com.ultra.nlp.manage.model.NlpData;
import com.ultra.nlp.manage.model.Page;

import java.util.Map;

/**
 * created by JIT on 2018/4/12
 */
public interface DataSetService {
    Page getDataAttr(Page page);

    Page getDateTypeList(Page page);

    Page getDataList(Page page);

    int  updateDataType(Integer id, String name, int attr);

    int updateDataAttr(Integer id, String name,String icon);

    int updateData(NlpData nlpData);

    int addDataAttr(Map<String,Object> map);

    int delDataAttr(int id) throws Exception;

    int addDataType(Map<String,Object> map);

    int delDataType(int id) throws Exception;

    int addData(Map<String,Object> map);

    int delData(int id);
}
