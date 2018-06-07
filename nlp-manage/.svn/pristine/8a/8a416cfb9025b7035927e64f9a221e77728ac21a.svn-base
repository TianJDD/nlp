package com.ultra.nlp.manage.service.impl;

import com.ultra.nlp.manage.dao.IUserDao;
import com.ultra.nlp.manage.model.User;
import com.ultra.nlp.manage.model.UserAdd;
import com.ultra.nlp.manage.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
@Service(value="userService")
public class UserServiceImpl implements IUserService {
    @Resource
    IUserDao iUserDao;
    @Override
    public User checkIfExist(Map<String, Object> map) {
        return iUserDao.checkIfExist(map);
    }

    @Override
    public int usertoadd(UserAdd useradd){
        return iUserDao.usertoadd(useradd);
    }
}
