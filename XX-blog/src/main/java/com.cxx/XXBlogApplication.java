package com.cxx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 陈喜喜
 * @date 2022-10-01 10:28
 */
@SpringBootApplication
@MapperScan("com.cxx.mapper")
@EnableScheduling
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class XXBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(XXBlogApplication.class,args);
    }

}
