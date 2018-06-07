package com.ultra.nlp.manage.service.impl;

import com.ultra.nlp.manage.dao.IAcademyDao;
import com.ultra.nlp.manage.model.*;
import com.ultra.nlp.manage.service.IAcademyService;
import com.ultra.nlp.manage.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("academyService")
public class AcademyServiceImp implements IAcademyService {
    @Resource
    IAcademyDao academyDao;

    @Override
    public Page getAcademyList(Page page) {
        page.setResultList(academyDao.getAcademyList(page.getParam()));
        int count = academyDao.getAcademyListCounts(page.getParam());
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    @Override
    public Page getGroupList(Page page){
        page.setResultList(academyDao.getGroupList(page.getParam()));
        int count = academyDao.getGroupListCounts(page.getParam());
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    @Override
    public Page getProfessorList(Page page){
        page.setResultList(academyDao.getProfessorList(page.getParam()));
        int count = academyDao.getprofessorListCounts(page.getParam());
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    @Override
    public int updateAcademy(NlpAcademy nlpAcademy)throws Exception{
        return academyDao.updateAcademy(nlpAcademy);
    }

    @Override
    public int updateProfessor(NlpProfessor nlpProfessor)throws Exception{
        return academyDao.updateProfessor(nlpProfessor);
    }

    @Override
    public int addAcademy(Map<String, Object> map)throws Exception {
        return academyDao.addAcademy(map);
    }

    @Override
    public int delAcademy(Map<String, Object> map) throws Exception {
        return academyDao.delAcademy(map);
    }

    @Override
    public int updateGroup(NlpGroup nlpGroup)  throws Exception{
        return academyDao.updateGroup(nlpGroup);
    }

    @Override
    public int delGroup(Map<String, Object> map) throws Exception {
        return academyDao.delGroup(map);
    }

    @Override
    public int delProfessor(Map<String, Object> map) throws Exception {
        return academyDao.delProfessor(map);
    }

    @Override
    public int addGroup(Map<String, Object> map) throws Exception {
        return academyDao.addGroup(map);
    }

    @Override
    public int addrPofessor(Map<String, Object> map) throws Exception {
        return academyDao.addrPofessor(map);
    }

    @Override
    public List getProfessoRelateList(String professorId) {
        return academyDao.getProfessoRelateList(professorId);
    }

    @Override
    public int updateProfessorRelate(NlpAccessProfessorRelate proRel) {
        return academyDao.updateProfessorRelate(proRel);
    }

    @Override
    public int addProfessorRelate(NlpAccessProfessorRelate proRel) {
        proRel.setId(StringUtil.getUUID());
        return academyDao.addProfessorRelate(proRel);
    }

    @Override
    public int delProfessorRelate(String id) {
        return academyDao.delProfessorRelate(id);
    }
}
