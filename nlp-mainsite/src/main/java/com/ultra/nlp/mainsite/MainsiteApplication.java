package com.ultra.nlp.mainsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
@EnableEurekaClient
@ServletComponentScan
public class MainsiteApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MainsiteApplication.class,args);
        System.out.println("*****************************************************");
        System.out.println("=====================mainsite 项目已启动====================");
        System.out.println("*****************************************************");
    }
}
