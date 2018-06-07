package com.ultra.nlp.manage.service.impl;

import com.ultra.nlp.manage.dao.FileuploadDao;
import com.ultra.nlp.manage.dao.ServerDao;
import com.ultra.nlp.manage.model.Page;
import com.ultra.nlp.manage.service.FileuploadService;
import com.ultra.nlp.manage.service.IServerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("fileuploadService")
public class FileuploadServiceImp implements FileuploadService {

    @Resource
    private FileuploadDao fileuploadDao;


    @Override
    public int addImgFile(Map<String, Object> map) throws Exception {
        return fileuploadDao.addImgFile(map);
    }

    @Override
    public int updateImgFile(Map<String, Object> map) throws Exception {
        fileuploadDao.deleteImgFile(map);
        return fileuploadDao.addImgFile(map);
    }

    @Override
    public Page getImgFileList(Page page) throws Exception {
        Map<String, Object> map = page.getParam();
        page.setResultList(fileuploadDao.getImgFileList(map));
        int count = fileuploadDao.getImgFileListCount(map);
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    @Override
    public Map<String, Object> selectImgFileDetail(Map<String, Object> map) throws Exception {
        return fileuploadDao.selectImgFileDetail(map);
    }

    @Override
    public int deleteImgFile(Map<String, Object> map) throws Exception {
        return fileuploadDao.deleteImgFile(map);
    }
}
