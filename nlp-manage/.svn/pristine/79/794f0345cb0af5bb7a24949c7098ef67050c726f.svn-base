package com.ultra.nlp.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ultra.nlp.manage.config.BaseTestConfig;
import com.ultra.nlp.manage.model.Classify;
import com.ultra.nlp.manage.model.NlpServiceManage;
import com.ultra.nlp.manage.model.NlpSolution;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSolutionController extends BaseTestConfig {

    @Test
    public void findSolutionList () throws Exception{
    /**
     *
     * 功能描述:
     * 查询解决方案列表
     * 用于后台管理系统中对解决方案信息维护前的列表查询
     * 提供可模糊查询功能，可模糊查询解决方案名称或者介绍。返回的列表按照创建时间倒序排序
     * @param:
     * @return:
     * @auther: guyuefei
     * @date:
     */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/solution/list")
                .param("keyword","哈尔滨")
                .param("pageNow","1")
                .param("pageSize","4")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void addSolution () throws Exception{
        /**
         *
         * 功能描述:
         * 用于后台管理系统中对解决方案信息维护添加解决方案
         * @param:
         * @return:
         * @auther: guyuefei
         * @date:
         */
        NlpSolution nlpSolution = new NlpSolution();
        nlpSolution.setSolutionName("测试新添加的解决方案");
        nlpSolution.setSolutionDesc("测试新添加的解决方案描述");
        nlpSolution.setAcademyId(1 +"");
        nlpSolution.setForwardType(1+"");
        nlpSolution.setSolutionIcon("aaa.png");
        nlpSolution.setSolutionField("测试");
        nlpSolution.setSolutionState("003002");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/solution/addSolution")
                .content(JSONObject.toJSONString(nlpSolution))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void updateSolution () throws Exception{
        /**
         *
         * 功能描述:
         * 用于后台管理系统中对解决方案信息维护编辑修改解决方案信息
         * 没有传的参数不更新
         * @param:
         * @return:
         * @auther: guyuefei
         * @date:
         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/solution/updateSolution")
                .param("solutionId","8")
                .param("solutionName","测试解决方案")
                .param("solutionDesc","测试解决方案的描述")
                .param("solutionIcon","ceshi.png")
                .param("solutionUrl","www.baidu.com")
                .param("forwardType","2")
                .param("solutionField","测试新")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void detail () throws Exception{
        /**
         *
         * 功能描述:
         * 管理系统解决方案信息维护查看解决方案详情
         * 根据解决方案详情id查询详情
         * @param:
         * @return:
         * @auther: guyuefei
         * @date:
         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/solution/detail")
                .param("solutionId","7")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }


    @Test
    public void delSolution () throws Exception{
        /**
         *
         * 功能描述:
         * 用于后台管理系统中对解决方案信息维护删除解决方案信息（逻辑删除）
         * 此处删除后，展示系统不再展示该条数据
         * @param:
         * @return:
         * @auther: guyuefei
         * @date:
         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/solution/delSolution")
                .param("solutionId","7")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }
}
