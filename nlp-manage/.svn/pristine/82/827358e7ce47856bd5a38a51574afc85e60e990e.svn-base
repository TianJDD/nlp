package com.ultra.nlp.manage.service.impl;

import com.ultra.nlp.manage.dao.ServerDao;
import com.ultra.nlp.manage.model.NlpAccessInfo;
import com.ultra.nlp.manage.model.NlpServerManage;
import com.ultra.nlp.manage.model.Page;
import com.ultra.nlp.manage.service.IServerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ServerServiceImp implements IServerService {

    @Resource
    private ServerDao serverDao;

    @Override
    public Map<String,Object> getServiceEntry(Map<String,Object> map) throws Exception{
        serverDao.getServiceEntry(map);
        map.put("accessInfoId",map.get("id"));
        return map;
    }

    @Override
    public Map<String,Object> addServerport(Map<String,Object> map) throws Exception{
        serverDao.addServerport(map);
        map.put("serverId",map.get("id"));
        return map;
    }

    @Override
    public int updateServiceEntry(Map<String, Object> map) throws Exception {
        return serverDao.updateServiceEntry(map);
    }
    @Override
    public int updateServerInfo(Map<String, Object> map) throws Exception {
        return serverDao.updateServerInfo(map);
    }
    @Override
    public Page getServerList(Page page) throws Exception {
        page.setResultList(serverDao.getServerList(page.getParam()));
        int count = serverDao.getServerListCount(page.getParam());
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    @Override
    public Map<String, Object> serviceEntryInfo(Map<String, Object> map) throws Exception{
        map = serverDao.serviceEntryInfo(map);
        return map;
    }

    @Override
    public int deleteServerPort(Map<String, Object> map) throws Exception {
        return serverDao.deleteServerPort(map);
    }
}
