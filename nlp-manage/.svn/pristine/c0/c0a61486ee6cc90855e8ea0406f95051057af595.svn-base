package com.ultra.nlp.manage.service.impl;

import com.ultra.nlp.manage.dao.PublicDao;
import com.ultra.nlp.manage.service.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Auther: admin
 * @Date: 2018/5/9 13:39
 * @Description:
 * @Usefor:
 * @param:
 * @Response:
 */
@Service("publicService")
public class PublicServiceImpl implements PublicService {
    @Resource
    PublicDao publicDao;
    @Override
    public Map<String,Object> checkIfExist(Map<String, Object> map) throws Exception{
        return publicDao.checkIfExist(map);
    }
}
