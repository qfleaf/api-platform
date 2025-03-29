package com.qfleaf.yunapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qfleaf.yunapi.mapper")
public class YunApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(YunApiApplication.class, args);
    }
}
