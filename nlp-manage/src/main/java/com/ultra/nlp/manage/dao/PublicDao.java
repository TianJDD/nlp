package com.ultra.nlp.manage.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;
import java.util.function.Predicate;

/**
 *
 * 功能描述:
 * 可抽离的公共查询方法
 * @param:
 * @return:
 * @auther: guyuefei
 * @date:
 */

@Mapper
public interface PublicDao {

    /**
     * 功能描述:
     *查询某种数据是否存在
     * @param:
     * @return:
     * @auther: guyuefei
     * @date:
     */
    Map<String,Object> checkIfExist(Map<String,Object> map) throws Exception;

}
