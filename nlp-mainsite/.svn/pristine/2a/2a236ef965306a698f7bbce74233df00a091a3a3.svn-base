package com.ultra.nlp.mainsite.config;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


//@RunWith(SpringJUnit4ClassRunner.class)//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文
//@SpringBootTest(classes=ManageApplication.class)// 用SpringJUnit4ClassRunner这个类需要指定spring-boot的启动类
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTestConfig {
    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setUp (){
        System.out.println("==============单元测试开始===============");
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @After
    public void after() {
        System.out.println("==============单元测试结束===============");
    }

}
