package com.qfleaf.yunapi;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@MapperScan("com.qfleaf.yunapi.mapper")
@ComponentScan({"com.qfleaf.yunapi.**", "com.qfleaf.web.*"})
@EnableTransactionManagement
@EnableDubbo
public class YunApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(YunApiApplication.class, args);
    }
}
