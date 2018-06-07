package com.ultra.nlp.manage.dao;

import com.ultra.nlp.manage.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IAcademyDao {
    List<Map<String, Object>> getAcademyList(Map<String, Object> map);

    int getAcademyListCounts(Map<String,Object> map);

    List<Map<String,Object>> getGroupList(Map<String,Object> map);

    int getGroupListCounts(Map<String,Object> map);

    List<Map<String, Object>> getProfessorList(Map<String, Object> map);

    int getprofessorListCounts(Map<String,Object> map);

    int updateAcademy(NlpAcademy nlpAcademy)throws Exception;

    int updateProfessor(NlpProfessor nlpProfessor)throws Exception;

    int addAcademy(Map<String, Object> map)throws Exception;

    int delAcademy(Map<String, Object> map) throws Exception;

    int updateGroup(NlpGroup nlpGroup) throws Exception;

    int delGroup(Map<String, Object> map) throws Exception;

    int delProfessor(Map<String, Object> map) throws Exception;

    int addGroup(Map<String, Object> map) throws Exception;

    int addrPofessor(Map<String, Object> map) throws Exception;

    List getProfessoRelateList(String professorId);

    int updateProfessorRelate(NlpAccessProfessorRelate proRel);

    int addProfessorRelate(NlpAccessProfessorRelate proRel);

    int delProfessorRelate(String id);
}
