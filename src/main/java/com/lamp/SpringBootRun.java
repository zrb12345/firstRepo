package com.lamp;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@EnableAsync
@RestController
@EnableTransactionManagement
@MapperScan(basePackages ={"com.lamp.*.service.mapper", "com.lamp.config", "com.lamp.*.*.*.service.mapper",})
@SpringBootApplication
public class SpringBootRun {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootRun.class, args);
    }
}
