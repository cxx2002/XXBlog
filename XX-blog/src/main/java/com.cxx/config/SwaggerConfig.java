package com.cxx.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cxx.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("嘻嘻博客", "https://github.com/cxx2002", "cxx2002@qq.com");
        return new ApiInfoBuilder()
                .title("嘻嘻博客")
                .description("嘻嘻博客前台接口管理")
                .contact(contact)   // 联系方式
                .version("1.1.1")  // 版本
                .build();
    }
}