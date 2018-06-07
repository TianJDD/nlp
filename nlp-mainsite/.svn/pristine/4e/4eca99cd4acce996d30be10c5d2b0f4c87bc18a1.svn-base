package com.ultra.nlp.mainsite.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * created by JIT on 2018/4/24
 */
@Mapper
public interface DataSetDao {

    List getDataSetType1();

    List getDataSetType2(Integer id);

    //查询默认全部行业数据
    List<Map<String, Object>> wholelist(Map<String, Object> param);
    int getwholelist(Map<String,Object> map);
}
