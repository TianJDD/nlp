package com.ultra.nlp.manage.controller;

import com.alibaba.fastjson.JSONObject;
import com.ultra.nlp.manage.config.BaseTestConfig;
import com.ultra.nlp.manage.model.NlpServiceManage;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFileOutputController  {

    private static File thisfile;
    private static File[] files;
    public static void main(String[] args){
        List<String> pathName = new ArrayList<String>();
        String dir = "F:\\IdeaProjects\\nlp-projects\\nlp-manage";
        List<String> filenames = TestFileOutputController.iteratorPath(dir,pathName);
        filenames.toArray();
        for (String str:filenames ) {
            System.out.println(str);
        }
    }

    public static List<String>  iteratorPath(String dir,List<String> pathName){
        thisfile = new File(dir);
        files = thisfile.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    pathName.add(file.getName());
                } else if (file.isDirectory()) {
                    iteratorPath(file.getAbsolutePath(),pathName);
                }
            }
        }
        return pathName;
    }
}
