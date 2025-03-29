package com.qfleaf.yunapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.qfleaf.yunapi.mapper")
@ComponentScan({"com.qfleaf.yunapi.**", "com.qfleaf.web.*"})
public class YunApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(YunApiApplication.class, args);
    }
}
