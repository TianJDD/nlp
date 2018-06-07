package com.ultra.nlp.mainsite.service.impl;

import com.ultra.nlp.mainsite.dao.IServiceDao;
import com.ultra.nlp.mainsite.model.*;
import com.ultra.nlp.mainsite.service.IServiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value="serviceService")
public class ServiceServiceImpl implements IServiceService {
    @Resource
    IServiceDao iServiceDao;

    @Override
    public Page searchServiceByCondition(Page page) {
        List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
        returnList = iServiceDao.searchServiceByCondition(page.getParam());
        int count = iServiceDao.searchServiceCountByKeyword(page.getParam());
        page.setResultList(returnList);
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    //产品服务列表展示(导航栏)
    @Override
    public Page getServiceTypeList(Page page) {
            List<Map<String, Object>> resultMap = iServiceDao.getServiceTypeList(page.getParam());
            List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
            for(Map<String, Object> map1 : resultMap){
                if(((String)map1.get("p_id")).equals("0")){//寻找第一级的服务
                    Map<String,Object> newmap = new HashMap<>();
                    newmap.put("id", map1.get("id"));
                    newmap.put("name", map1.get("name")+"");
                    newmap.put("img", map1.get("img")+"");
                    newmap.put("ser_type", map1.get("ser_type"));
                    newmap.put("descr", map1.get("descr"));
                    newmap.put("icon",map1.get("icon"));
                    returnList.add(newmap);
                }else{//不是第一级的服务
                    returnList = (List<Map<String, Object>>) this.resolvedept(returnList,map1);
                }

            }
            int count = iServiceDao.getServiceTypeListCounts(page.getParam());
            page.setResultList(returnList);
            if(page.getPageSize() == 0)
                page.setPageCount(1);
            else
                page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
            page.setRowCount(count);
            return page;
        }

    @Override
    public Page getRecServiceList2(Page page) {
        List<Map<String,Object>> returnList = iServiceDao.getRecServiceList2(page.getParam());
        List<Map<String,Object>> list = new ArrayList<>();
        for (int i = 0; i < returnList.size() ; i++) {
            Map<String, Object> map = returnList.get(i);
            String id = (String)map.get("id") ;
            list = iServiceDao.getService(id);
            map.put("children",list);
        }
        int count = iServiceDao.getServiceTypeListCounts2(page.getParam());
        page.setResultList(returnList);
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }


    @Override
    public Page getRecClsList(Page page) {
        List<Map<String, Object>> returnlist = iServiceDao.getRecClsList(page.getParam());
        int count = iServiceDao.getRecClsListCounts(page.getParam());
        page.setResultList(returnlist);
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    //查询热门服务下的第一级
    @Override
    public Page getRecServiceList1(Page page) {
        List<Map<String, Object>> returnlist = iServiceDao.getRecServiceList1(page.getParam());

        page.setResultList(returnlist);
        /*int count = iServiceDao.getRecClsListCounts(page.getParam());
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);*/
        return page;
    }

    //根据type3_id查询服务列表
    @Override
    public Page getServiceListByType3Id(Page page) {
        List<Map<String, Object>> returnlist = iServiceDao.getServiceListByType3Id(page.getParam());
        int count = iServiceDao.getServiceListByType3IdCounts(page.getParam());
        page.setResultList(returnlist);
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }


    //遍历查询，迭代
        Object resolvedept(List<Map<String,Object>> list,Map<String,Object> child){
            for(Map<String,Object> map : list){
                //寻找上一级中自己的父级，如果上一级没找到向上一级寻找
                List<Map<String,Object>> cList = (List<Map<String,Object>>)map.get("children");
                if(null != cList && cList.size() > 0){
                    if((child.get("p_id")).equals(map.get("id"))){
                        Map<String,Object> children = new HashMap<>();
                        children.put("id", child.get("id"));
                        children.put("name", child.get("name"));
                        children.put("descr",child.get("descr"));
                        children.put("img", child.get("img"));
                        children.put("icon",child.get("icon"));
                        cList.add(children);
                    }
                    resolvedept(cList,child);
                }else{
                    if(child.get("p_id").equals(map.get("id"))){
                        List<Map<String,Object>> ch = new ArrayList<>();
                        Map<String,Object> children = new HashMap<>();
                        children.put("id", child.get("id"));
                        children.put("name", child.get("name"));
                        children.put("descr",child.get("descr"));
                        children.put("img", child.get("img"));
                        children.put("icon",child.get("icon"));
                        ch.add(children);
                        map.put("state", "closed");
                        map.put("children", ch);
                    }
                }
            }
            return list;
        }
    @Override
    public ServiceDetail detailslist(Map<String,Object> map){

//        List<Map<String,Object>> list = iServiceDao.detailsdao(id);
        ServiceDetail sd = iServiceDao.getServiceInfo(map);
        return sd;
    }

    @Override
    public Map<String, Object> getServiceIpAndPort(Map<String, Object> map) {
        return iServiceDao.getServiceIpAndPort(map);
    }

    @Override
    public int updateServiceUseCount(Map<String, Object> map) {
        return iServiceDao.updateServiceUseCount(map);
    }

    @Override
    public Map<String, Object> getServiceTypeNameById(Map<String, Object> map) {
        return iServiceDao.getServiceTypeNameById(map);
    }

    @Override
    public List getSolutionList() {
        return iServiceDao.getSolutionList();
    }

    @Override
    public Map<String, Object> checkServiceDetailForward(Map<String, Object> map) {
        return iServiceDao.checkServiceDetailForward(map);
    }

}

