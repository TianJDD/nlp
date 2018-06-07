package com.ultra.nlp.mainsite.controller;

import com.ultra.nlp.mainsite.config.BaseTestConfig;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestRestServiceController extends BaseTestConfig {

    @Test
    public void execute1 () throws Exception{
        /**
         * 功能描述: 测试调用鼎富服务（REST服务）
         * @param: type 用以区别服务
         * @param: url 服务调用路径
         * @param: param 入参
         * @return:
         * @auther: guyuefei
         * @date:
         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/execute/service")
                .param("type","001001")
                .param("url","http://60.247.77.164/nlpapi/basic_algorithm/segment_cn/max_cut_pos")
                .param("param","{\"text\":[\"迈向充满希望的新世纪\",\"训练语料之外句子\",\"武核俄器如方此\"],\"natureSelectedArray\":[\"v\",\"nz\"]}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

   @Test
    public void execute2 () throws Exception{
       /**
        * 功能描述: 测试调用websokect服务
        * @param: type 用以区别服务
        * @param: url 服务调用路径
        * @param: param 入参
        * @return:
        * @auther: guyuefei
        * @date:
        */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/execute/service")
                .param("type","001002")
                .param("url","ws://hlt-la.suda.edu.cn")
                .param("param","我是中国人")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

}

