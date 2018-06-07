package com.ultra.nlp.manage.dao;

import com.ultra.nlp.manage.model.NlpDict;
import com.ultra.nlp.manage.model.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DictDao {

    List<Map<String,Object>> getDictList(Map<String,Object> map);

    int getDictToAdd(NlpDict nlpDict);

    String getDictUUid(NlpDict nlpDict) throws Exception;

    int updateDict(NlpDict nlpDict);

    List<Map> getDictTreeList(Map<String, Object> param);

    int getDictListCounts(Map<String, Object> param);

    int getDictTreeListCount(Map<String, Object> param);

    int delDict(String dictCode);
}
