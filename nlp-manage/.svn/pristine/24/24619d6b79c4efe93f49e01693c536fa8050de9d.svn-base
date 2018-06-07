package com.ultra.nlp.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ultra.nlp.manage.config.BaseTestConfig;
import com.ultra.nlp.manage.model.NlpAcademy;
import com.ultra.nlp.manage.model.NlpGroup;
import com.ultra.nlp.manage.model.NlpProfessor;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class TestFileUploadController extends BaseTestConfig {

    @Test
    public void imgFileList () throws Exception{
        /**
         * 查询图片文件列表
         * 用例
         * 1、输入条件：更改pageNow或者pageSize大小，fileType为图片类型
         *    响应结果：返回查询当前页的图片列表，条数为pageSize大小
         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/manage/file/list")
                .param("fileType","013003")
                .param("pageNow","1")
                .param("pageSize","10")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void addImgFile () throws Exception{
        /**
         * 保存文件路径
         * 用途：用于管理系统上传图片等文件存储路径到数据库
         * @auther: guyuefei
         * @date:
         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/manage/file/addImgFile")
                .param("fileDesc","svg图片")
                .param("fileType","013003")
                .param("fileUrl","http://www.china-nlp.com/nlp_static/serviceType_001001.svg")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void updateImgFile () throws Exception{
        /**
         * 修改文件存储信息
         * 用途：用于管理系统修改图片等文件数据库中的存储信息
         * 2、参数：id
         * 3、逻辑：根据id修改数据库中对应的数据
         * @auther: guyuefei
         * @date:
         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/manage/file/updateImgFile")
                .param("imageId","f5946a81be66427ea1f2293f0c4c4c43")
                .param("fileDesc","svg图标")
                .param("fileType","013003")
                .param("fileUrl","http://www.china-nlp.com/nlp_static/serviceType_001001.svg")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }
    @Test
    public void imgdetail () throws Exception{
        /**
         * 查询图片文件详情
         * 用途：用于所有查询图片详情
         * @param: imageId 图片主键id
         * @return: 图片详情
         * @auther: guyuefei
         * @date:
         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/manage/file/imgdetail")
                .param("imageId","f5946a81be66427ea1f2293f0c4c4c43")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

    @Test
    public void deleteImg () throws Exception{
        /**
         * 删除数据库中图片表的数据
         * 用途：用于管理系统对图片的维护-删除操作
         * @param: id 部门id
         * @return: 删除是否成功信息
         * @auther: guyuefei
         * @date:
         */
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/manage/file/deleteImg")
                .param("imageId","1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        System.out.println("==============单元测试完成===============");
        System.out.println("返回信息：" + result.getResponse().getContentAsString());
    }

}
