package com.ultra.nlp.manage.service.impl;

import com.ultra.nlp.manage.dao.IServiceDao;
import com.ultra.nlp.manage.model.Classify;
import com.ultra.nlp.manage.model.NlpServiceManage;
import com.ultra.nlp.manage.model.Page;
import com.ultra.nlp.manage.service.IServiceService;
import com.ultra.nlp.manage.util.FormatUtil;
import com.ultra.nlp.manage.util.GenerateCode;
import com.ultra.nlp.manage.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page getClassifyTreeList(Page page) {
        List<Map<String, Object>> resultlist = iServiceDao.getClassifyList(page.getParam());

        List<Map<String,Object>> returnList = new ArrayList<Map<String,Object>>();
        for(Map<String, Object> map1 : resultlist){
            if(((String)map1.get("p_id")).equals("0")){//寻找第一级的服务
                Map<String,Object> newmap = new HashMap<>();
                newmap.put("id", map1.get("id"));
                newmap.put("name", map1.get("name")+"");
                newmap.put("type", "service");
                returnList.add(newmap);
            }else{//不是第一级的服务
                returnList = (List<Map<String, Object>>) this.resolveClassify(returnList,map1);
            }

        }
        page.setResultList(returnList);
        int count = iServiceDao.getClassifyListCounts(page.getParam());
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    @Override
    public Page getClassifyList(Page page) {
        List<Map<String, Object>> returnlist = iServiceDao.getClassifyList(page.getParam());
        page.setResultList(returnlist);
        int count = iServiceDao.getClassifyListCounts(page.getParam());
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }
    //遍历查询，迭代
    Object resolveClassify(List<Map<String,Object>> list,Map<String,Object> child){
        for(Map<String,Object> map : list){
            //寻找上一级中自己的父级，如果上一级没找到向上一级寻找
            List<Map<String,Object>> cList = (List<Map<String,Object>>)map.get("children");
            if(null != cList && cList.size() > 0){
                if((child.get("p_id")).equals(map.get("id"))){
                    Map<String,Object> children = new HashMap<>();
                    children.put("id", child.get("id"));
                    children.put("name", child.get("name"));
                    children.put("type", "service");
                    cList.add(children);
                }
                resolveClassify(cList,child);
            }else{
                if(child.get("p_id").equals(map.get("id"))){
                    List<Map<String,Object>> ch = new ArrayList<>();
                    Map<String,Object> children = new HashMap<>();
                    children.put("id", child.get("id"));
                    children.put("name", child.get("name"));
                    children.put("type", "service");
                    ch.add(children);
                    map.put("state", "closed");
                    map.put("children", ch);
                }
            }
        }
        return list;
    }
    @Override
    public int addClassify(Classify classify) {
        //getMaxSubId()通过p_id去数据库中查询它下边的最大的子级分类id,如果不存在，设置返回值是1，然后判断返回值位数，
        //最后将p_id和生成的code拼接
        String p_id = classify.getP_id();
        if (p_id.equals("0")){
            String id = GenerateCode.generateCode(Integer.parseInt(iServiceDao.getMaxSubId(p_id) == null ? 1 + "" : iServiceDao.getMaxSubId(p_id)));
            classify.setId(id);
            classify.setIcon("serviceType_" + id);
            return iServiceDao.addClassify(classify);
        }
        String id = p_id + GenerateCode.generateCode(Integer.parseInt(iServiceDao.getMaxSubId(p_id) == null ? 1 + "" : iServiceDao.getMaxSubId(p_id)));
        classify.setId(id);
        classify.setIcon("serviceType_" + id);
        return iServiceDao.addClassify(classify);
    }

    @Override
    public int delClassify(int id) {
        return iServiceDao.delClassify(id);
    }

    @Override
    public int updateClassify(Classify classify) throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> returnMap = new HashMap<String,Object>(7);
        //首先根据前端传过来的p_id跟需要更改的分类的p_id进行比较，如果相同则，分类的id和p_id均不需要修改，
        //如果不相同，则分类的id需要重新生成，生成规则同添加分类
        map.put("id",classify.getId());
        returnMap = iServiceDao.classifyDetail(map);
        String p_id = classify.getP_id();
        if (p_id.equals("0") && ! returnMap.get("p_id").equals(p_id)){
                String new_id =GenerateCode.generateCode(Integer.parseInt(iServiceDao.getMaxSubId(p_id) == null ? 1 + "" : iServiceDao.getMaxSubId(p_id)));
                map.put("new_id",new_id);
                map.put("p_id",p_id);
        }else if (! p_id.equals("0") && ! returnMap.get("p_id").equals(p_id)){
            String new_id =p_id + GenerateCode.generateCode(Integer.parseInt(iServiceDao.getMaxSubId(p_id) == null ? 1 + "" : iServiceDao.getMaxSubId(p_id)));
            map.put("new_id",new_id);
            map.put("p_id",p_id);
        }
        map.put("name",classify.getName());
        map.put("descr",classify.getDescr());
        map.put("icon",classify.getIcon());
        map.put("img",classify.getImg());
        map.put("leaf",classify.getLeaf());
        map.put("rec_num",classify.getLeaf());
        return iServiceDao.updateClassify(map);
    }

    @Override
    @Transactional
    public int addServiceManage(NlpServiceManage nlpServiceManage) throws Exception{
        //isCharge 是否收费属性，如果前台不给设置，数据库设置默认给状态012001免费状态。
        String createTime = FormatUtil.DateFormat();
        nlpServiceManage.setId(StringUtil.getUUID());
        nlpServiceManage.setCreateTime(createTime);
        nlpServiceManage.setUpdateTime(createTime);
        nlpServiceManage.setRel_time(FormatUtil.getLongTime());
        nlpServiceManage.setUse_num(0);
        nlpServiceManage.setServiceType(nlpServiceManage.getClass_id().substring(0,3));
        nlpServiceManage.setAccessTime(createTime);
        iServiceDao.addServiceManage(nlpServiceManage);
        List<Map<String,Object>> relates = nlpServiceManage.getRelates();
        //添加服务相关信息
        if(relates != null && relates.size() > 0){
            for(Map<String,Object> relate : relates){
                relate.put("serviceId",nlpServiceManage.getId());
                relate.put("id",StringUtil.getUUID());
                iServiceDao.addServiceManageRelate(relate);
            }
        }
        return 1;
    }

    @Override
    public int updateServiceManage(NlpServiceManage nlpServiceManage) {
        List<Map<String, Object>> relates = nlpServiceManage.getRelates();
        String id = nlpServiceManage.getId();
        //判断nlpServiceManage中的relates是否为空，不为空，对nlp_service_relate表进行修改
        //思路：通过serviceId去nlp_service_relate表中进行删除，再重新添加，拿到relateType，进行重新添加
        if (relates !=null && relates.size() > 0){
            Map map = new HashMap(1);
            map.put("serviceId",id);
            int i = iServiceDao.delServiceRelate(map);
            for (Map<String,Object> relate : relates ) {
                relate.put("serviceId",nlpServiceManage.getId());
                relate.put("id",StringUtil.getUUID());
                iServiceDao.addServiceRelate(relate);
            }
        }
        return iServiceDao.updateServiceManage(nlpServiceManage);
    }

    @Override
    public int delService(int id) {
        return iServiceDao.delService(id);
    }

    @Override
    public Page getServiceList(Page page) {
        //通过传过来的id去nlp_classify表查询它的子级
        List<Map<String, Object>> returnlist = iServiceDao.getClassifyList(page.getParam());
        //去nlp_service_manage表查询属于所传id分类的服务集合
        List<Map<String, Object>> result = iServiceDao.getServiceList(page.getParam());
        //page.setResultList(returnlist);
        Map<String, Object> map = new HashMap<>();
        if (returnlist.size() > 0){
            map.put("isLastClassify",false);
        } else {
            map.put("isLastClassify",true);
        }
        map.put("result",result);
        page.setResultMap(map);
        int count = iServiceDao.getServiceListCounts(page.getParam());
        if(page.getPageSize() == 0)
            page.setPageCount(1);
        else
            page.setPageCount(count % page.getPageSize() == 0 ? count % page.getPageSize() : count % page.getPageSize() + 1);
        page.setRowCount(count);
        return page;
    }

    @Override
    public Map<String, Object> serviceDetail(Map<String, Object> map) {
        List<Map<String,Object>> relates = iServiceDao.serviceDetailRelate(map);
        map = iServiceDao.serviceDetail(map);
        map.put("relates",relates);
        return map;
    }
}

