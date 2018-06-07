package com.ultra.nlp.mainsite.dao;

import com.ultra.nlp.mainsite.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * created by JIT on 2018/4/27
 */

@Mapper
public interface userDao {
    User selectUser(@Param("name") String name, @Param("password") String password);

    int updatePassword(@Param("name") String name,@Param("OldPassword")String OldPassword,@Param("NewPassword")String NewPassword);
}
