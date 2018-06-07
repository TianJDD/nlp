package com.ultra.nlp.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ultra.nlp.manage.config.BaseTestConfig;
import com.ultra.nlp.manage.model.NlpDict;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Auther: admin
 * @Date: 2018/5/9 14:20
 * @Description:
 * @Usefor:
 * @param:
 * @Response:
 */
public class TestPublicController extends BaseTestConfig {

    @Test
    /**
     *
     * 功能描述:
     *查询数据是否已存在接口
     *  根据传入类型判断是否数据已存在
     * @param: type : academy - 大学  group - 机构/部门  professor - 教授  service - 服务  classify - 服务分类 user - 用户
     * @param: name：查询名称
     * @return:
     * @auther: guyuefei
     * @date:
     */
    public void checkIfExist () throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/public/checkIfExist")
                .param("type","user")
                .param("name","admin")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }
}
