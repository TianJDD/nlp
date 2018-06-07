package com.ultra.nlp.manage.service;

import com.ultra.nlp.manage.model.NlpSolution;
import com.ultra.nlp.manage.model.Page;

import java.util.Map;

/**
* 解决方案相关接口 service接口
* @author guyuefei
* @date 2018-05-14 14:07:52
*/
public interface SolutionService {

    Page findSolutionList(Page page);

    NlpSolution addSolution(NlpSolution nlpSolution) throws Exception;

    int updateSolution(Map<String,Object> map) throws Exception;

    int delSolution(Map<String,Object> map) throws Exception;

    Map<String,Object> solutionDetail(Map<String,Object> map) throws Exception;
}