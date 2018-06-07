package com.ultra.nlp.mainsite.controller;

import com.ultra.nlp.mainsite.config.BaseTestConfig;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * created by JIT on 2018/4/12
 */
public class TestDetailsController extends BaseTestConfig {

   /* @Test
    public void updateDataSet () throws Exception{

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/dataset/update")
                .param("name","1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void addDataAttr () throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/dataset/attr/add")
                .param("name","开发数据")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void delDataAttr () throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/dataset/attr/del")
                .param("id","8")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void addDataType () throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/dataset/dataType/add")
                .param("name","abc")
                .param("attr","3")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }*/

    @Test
    public void delData () throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/detailsImp/details/list")
                .param("id","1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    /*@Test
    public void addData () throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/dataset/data/add")
                .param("name","abc")
                .param("uploader","abc")
                .param("introduce","abc")
                .param("size","3")
                .param("format","abc")
                .param("url","3")
                .param("type","1")
                .param("type_name","aaaa")
                .param("img","abc")
                .param("attr","1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void delDataType () throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/dataset/data/del")
                .param("id","75")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }*/


    @Test
    public void testDownload () throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/dataset/testDownload")
                .param("id","1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }
}
