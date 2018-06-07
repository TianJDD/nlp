package com.ultra.nlp.manage.dao;

import com.ultra.nlp.manage.model.User;
import com.ultra.nlp.manage.model.UserAdd;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface IUserDao {
    User checkIfExist(Map<String,Object> map);

    int usertoadd(UserAdd useradd);
}
