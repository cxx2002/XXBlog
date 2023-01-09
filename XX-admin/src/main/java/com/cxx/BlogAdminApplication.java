package com.cxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 陈喜喜
 * @date 2022-10-10 15:39
 */
@SpringBootApplication
@MapperScan("com.cxx.mapper")
@EnableSwagger2
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class,args);
        System.out.println("k");
    }
}
