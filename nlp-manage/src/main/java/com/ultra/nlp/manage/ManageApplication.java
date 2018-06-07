package com.ultra.nlp.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class ManageApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ManageApplication.class,args);
        System.out.println("*****************************************************");
        System.out.println("=====================manage 项目已启动====================");
        System.out.println("*****************************************************");
    }
}
