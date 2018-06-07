package com.ultra.nlp.manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Auther: admin
 * @Date: 2018/5/15 15:11
 * @Deption:
 * @Usefor:
 * @param:
 * @Response:
 */
@Configuration
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ultra.nlp.manage.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("NLP平台管理系统接口文档说明")
                .description("这一部分为平台管理系统，主要进行信息录入")
                .termsOfServiceUrl("http://192.168.95.57:8090")
                .version("1.0")
                .build();
    }
}
