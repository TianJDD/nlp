package com.ultra.nlp.mainsite;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class SpringBootStartApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return application.sources(MainsiteApplication.class);
    }

}
