package com.ultra.nlp.mainsite.service.impl;

import com.ultra.nlp.mainsite.dao.DataSetDao;
import com.ultra.nlp.mainsite.model.Page;
import com.ultra.nlp.mainsite.service.DataSetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * created by JIT on 2018/4/24
 */
@Service
public class DataSetServiceImpl implements DataSetService{

    @Resource
    private DataSetDao dataSetDao;

    @Override
    public List getDataSetType1() {
        return dataSetDao.getDataSetType1();
    }

    @Override
    public List getDataSetType2(Integer id) {
        return dataSetDao.getDataSetType2(id);
    }

    @Override
    public Page wholelist(Page page) {
        List<Map<String, Object>> wholelist = dataSetDao.wholelist(page.getParam());
        int count = dataSetDao.getwholelist(page.getParam());
        System.out.println(count);
        page.setResultList(wholelist);
        if (page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }
}
