package com.ultra.nlp.manage.service;

import com.ultra.nlp.manage.model.*;

import java.util.List;
import java.util.Map;

public interface IAcademyService {

    Page getAcademyList(Page page);

    Page getGroupList(Page page);

    Page getProfessorList(Page page);

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
