package com.ultra.nlp.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ultra.nlp.manage.config.BaseTestConfig;
import com.ultra.nlp.manage.model.UserAdd;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestUserController extends BaseTestConfig {

    @Test
    public void login () throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/login")
                .param("name","1")
                .param("password","")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    /**
     * 用户添加测试
     */
    public void Add () throws Exception{
        UserAdd useradd = new UserAdd();
        useradd.setUserName("zhangyu");
        useradd.setUserPhone("1");
        useradd.setUserMail("zhangyu@163.com");
        useradd.setUserInfo("asdfsa");
        useradd.setUserPwd("123456");
        useradd.setDictCode("2");
        useradd.setSalt("asffg");
        useradd.setUserImg("123");
        useradd.setUserStatus("2");
        useradd.setCreateTime("");
        useradd.setUpdateTime("");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user/UserAdd")
                .content(JSONObject.toJSONString(useradd))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }
}
