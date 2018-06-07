package com.ultra.nlp.manage.service.impl;

import com.ultra.nlp.manage.dao.DataSetDao;
import com.ultra.nlp.manage.model.NlpData;
import com.ultra.nlp.manage.model.Page;
import com.ultra.nlp.manage.service.DataSetService;
import com.ultra.nlp.manage.util.FormatUtil;
import com.ultra.nlp.manage.util.GenerateCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by JIT on 2018/4/12
 */
@Service
@Transactional
public class DataSetServiceImpl implements DataSetService {

    @Resource
    private DataSetDao dataSetDao;

    @Override
    public Page getDataAttr(Page page) {
        List<Map<String, Object>> returnlist = dataSetDao.getDataAttrList(page.getParam());
        int count = dataSetDao.getDataAttrListCounts(page.getParam());
        page.setResultList(returnlist);
        if (page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    @Override
    public Page getDateTypeList(Page page) {
        List<Map<String, Object>> returnlist = dataSetDao.getDataTypeList(page.getParam());
        int count = dataSetDao.getDataTypeListCounts(page.getParam());
        page.setResultList(returnlist);
        if (page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    @Override
    public Page getDataList(Page page) {
        List<Map<String, Object>> returnlist = dataSetDao.getDataList(page.getParam());
        int count = dataSetDao.getDataListCounts(page.getParam());
        page.setResultList(returnlist);
        if (page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    @Override
    public int updateDataType(Integer id, String name, int attr) {
        return dataSetDao.updateDataType(id, name, attr);
    }

    @Override
    public int updateDataAttr(Integer id, String name, String icon) {
        return dataSetDao.updateDataAttr(id, name, icon);
    }

    @Override
    public int updateData(NlpData nlpData) {
        return dataSetDao.updateData(nlpData);
    }

    /**
     * 设计思路：通过查询出1000之内的最大id,对其+1作为新插入对象的id.
     *
     * @date 2018/5/15 15:17
     * last modified by JIT
     */ 
    @Override
    public int addDataAttr(Map<String, Object> map) {
        int attr = Integer.parseInt((String) map.get("attr"));
        int id = 0;
        if (null == dataSetDao.getMaxIdByAttr(attr)) {
            id = attr * 1000 + 1;
        } else {
            id = Integer.parseInt(dataSetDao.getMaxIdByAttr(attr)) + 1;
        }
        String newId = "00" + id;
        System.out.println(newId);
        map.put("id", newId);
        return dataSetDao.addDataAttr(map);
    }

    @Override
    public int delDataAttr(int id) throws Exception {
        Map<String, Object> map = new HashMap(3);
        map.put("attr", id);
        //根据attr删除type表中的数据
        dataSetDao.delDataTypeByIdOrAttr(map);
        //根据attr删除data表中的数据
        dataSetDao.delDataByTypeOrAttr(map);
        return dataSetDao.delDataAttr(id);
    }

    @Override
    public int addDataType(Map<String, Object> map) {
        return dataSetDao.addDataType(map);
    }

    @Override
    public int delDataType(int id) throws Exception {
        Map<String, Object> map = new HashMap<>(1);
        //根据type删除该type下的数据集
        map.put("type", id);
        dataSetDao.delDataByTypeOrAttr(map);
        map.put("id", id);
        return dataSetDao.delDataTypeByIdOrAttr(map);
    }

    @Override
    public int addData(Map<String, Object> map) {
        String typeId = GenerateCode.generateCode((int)map.get("typeId"),"2");
        String attr = GenerateCode.generateCode((int) map.get("attr"));
        String uploadTime = FormatUtil.getLongTime();
        map.put("uploadTime",uploadTime);
        map.put("typeId",typeId);
        map.put("attr",attr);
        return dataSetDao.addData(map);
    }

    @Override
    public int delData(int id) {
        return dataSetDao.delData(id);
    }
}
