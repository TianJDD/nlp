package com.ultra.nlp.mainsite.controller;

import com.ultra.nlp.mainsite.config.BaseTestConfig;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestServiceController extends BaseTestConfig {

    @Test
    public void ServiceTypeList () throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/service/type/list")
                .param("pageNow","1")
                .param("pageSize","0")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    /**
     * 导航栏平台服务列表展示
     * @throws Exception
     */
    @Test
    public void getServiceList () throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/service/type/headList")
                .param("pageNow","1")
                .param("pageSize","100")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void serchServiceByCondition () throws Exception{
        //需要测试useNum==1的情况、relTime==1的情况、useNum==2的情况、relTime==2的情况
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/service/serchServiceByCondition")
                .param("pageNow","1")
                .param("pageNow","1")
                .param("pageSize","0")
                .param("useNum","1")
                .param("relTime","1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }
    @Test
    public void detailslist () throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/service/details/list")
                .param("id","1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void ExecuteService () throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/execute/service")
                .param("param","{\"text\":[\"迈向充满希望的新世纪\",\"训练语料之外句子\",\"武核俄器如方此\"],\"natureSelectedArray\":[\"v\",\"nz\"]}")
                .param("url","")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void getServiceTypeNameById () throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/service/getServiceTypeNameById")
                .param("class_id","001002001")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }
}

