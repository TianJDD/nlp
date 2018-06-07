package com.ultra.nlp.manage.dao;

import com.ultra.nlp.manage.model.NlpSolution;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* 解决方案相关接口 dao接口
* @author guyuefei
* @date 2018-05-14 14:07:52
*/
@Mapper
public interface SolutionDao {

    /**
     * @param map
     * @return
     */
    List<Map<String, Object>> findSolutionList(Map<String,Object> map);

    int findSolutionListCounts(Map<String,Object> map);

    int addSolution(NlpSolution nlpSolution) throws Exception;

    int updateSolution(Map<String,Object> map) throws Exception;

    Map<String,Object> solutionDetail(Map<String,Object> map) throws Exception;

}