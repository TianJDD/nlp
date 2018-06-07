package com.ultra.nlp.mainsite.service.impl;

import com.ultra.nlp.mainsite.dao.IServiceDao;
import com.ultra.nlp.mainsite.dao.ISolutionDao;
import com.ultra.nlp.mainsite.service.ISolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value="solutionService")
public class SolutionServiceImpl implements ISolutionService {
    @Resource
    ISolutionDao iSolutionDao;


    @Override
    public List getSolutionList() {
        return iSolutionDao.getSolutionList();
    }

}

