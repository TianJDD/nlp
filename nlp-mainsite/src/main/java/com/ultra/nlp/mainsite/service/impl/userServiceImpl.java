package com.ultra.nlp.mainsite.service.impl;

import com.ultra.nlp.mainsite.dao.userDao;
import com.ultra.nlp.mainsite.model.User;
import com.ultra.nlp.mainsite.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * created by JIT on 2018/4/27
 */
@Component
public class userServiceImpl implements userService {

    @Resource
    private userDao userDao;

    @Override
    public User selectUser(String name, String password) {
        return userDao.selectUser(name,password);
    }

    @Override
    public int updatePassword(String name,String OldPassword,String NewPassword) {
        return userDao.updatePassword(name,OldPassword,NewPassword);
    }
}
