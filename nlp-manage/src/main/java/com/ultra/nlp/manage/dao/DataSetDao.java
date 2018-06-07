package com.ultra.nlp.manage.dao;

import com.ultra.nlp.manage.model.NlpData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * created by JIT on 2018/4/12
 */
@Mapper
public interface DataSetDao {

    List<Map<String, Object>> getDataAttrList(Map<String,Object> map);

    List<Map<String, Object>> getDataTypeList(Map<String,Object> map);

    List<Map<String,Object>> getDataList(Map<String, Object> param);

    int getDataAttrListCounts(Map<String,Object> map);

    int getDataTypeListCounts(Map<String, Object> map);

    int getDataListCounts(Map<String, Object> param);

    int updateDataAttr(@Param("id") Integer id, @Param("name")String name ,@Param("icon") String icon );

    int updateDataType(@Param("id") Integer id, @Param("name") String name, @Param("attr") int attr);

    int updateData(NlpData nlpData);

    int addDataAttr(Map<String,Object> map);

    int delDataAttr(int id);

    int addDataType(Map<String,Object> map);

    int delDataTypeByIdOrAttr(Map<String,Object> map);

    int addData(Map<String,Object> map);

    int delData(int id);
    //根据type类型删除该type类型下的数据集
    int delDataByTypeOrAttr(Map<String,Object> map);

    String getMaxIdByAttr(int attr);
}
