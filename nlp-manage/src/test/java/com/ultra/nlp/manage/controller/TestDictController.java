package com.ultra.nlp.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ultra.nlp.manage.config.BaseTestConfig;
import com.ultra.nlp.manage.model.NlpDict;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestDictController extends BaseTestConfig {

    @Test
    public void selectDictList () throws Exception{
        /**
         *
         * 字典表查询接口
         * @param request
         * @param response
         * @param nlpDict
         * 应用场景：用于查询字典表
         * 逻辑：当传参parentCode有值时根据parentCode查询其子类列表，不需要分页，当传参dictCode有值时，查询某条信息
         * 结果：修改字典表成功。
         * @auther: guyuefei
         * @date:
         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/dict/list")
                .param("parentCode","001")
                .param("dictCode","001001")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }
    @Test
    public void Add () throws Exception{
        /**
         * 字典表添加服务
         * @param nlpDict
         * 应用场景：用于添加服务
         * 逻辑：前端将需要添加的参数请求发送给后台，控制器在后台用对象nlpDict 接收，
         * 将请求发送到业务层处理业务，业务执行成功后将生成的子id：dictCode  返回前台
         * 结果：添加字典表成功。
         * @auther: guyuefei
         * @date:
         */
        NlpDict nlpDict = new NlpDict();
        nlpDict.setDictName("已删除");
        nlpDict.setParentCode("010");
        nlpDict.setParentName("一般状态");
        nlpDict.setDictState(1);
        nlpDict.setCreateTime("");
        nlpDict.setUpdateTime("");
        nlpDict.setCreateUser("admin");
        nlpDict.setUpdateUser("admin");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/dict/dictadd")
                .content(JSONObject.toJSONString(nlpDict))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void updateDict () throws Exception{
        /**
         *
         * 字典表修改服务
         * @param request
         * @param response
         * @param nlpDict
         * 应用场景：用于修改字典表服务
         * 逻辑：前端将要修改的参数请求发送到控制器，控制器在后台用对象nlpDict 接收，
         * 将请求发送到业务层处理业务，业务执行成功不需要向前台返回json。
         * 结果：修改字典表成功。
         * @auther: guyuefei
         * @date:
         */
        NlpDict nlpDict = new NlpDict();
        nlpDict.setDictCode("0010");
        nlpDict.setDictName("zhangyu");
        nlpDict.setParentName("zhangyu");
        nlpDict.setDictState(2);
        nlpDict.setUpdateTime("");
        nlpDict.setUpdateUser("zhangyu");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/dict/updatedict")
                .content(JSONObject.toJSONString(nlpDict))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }
}
