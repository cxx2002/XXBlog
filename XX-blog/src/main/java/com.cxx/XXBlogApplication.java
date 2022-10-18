package com.cxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 陈喜喜
 * @date 2022-10-01 10:28
 */
@SpringBootApplication
@MapperScan("com.cxx.mapper")
@EnableScheduling
@EnableSwagger2
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class XXBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(XXBlogApplication.class,args);
    }

}
