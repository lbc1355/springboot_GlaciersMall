package com.lioch3cooh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.lioch3cooh.glaciersmall.dao")
@SpringBootApplication
public class application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run =
                SpringApplication.run(application.class, args);
    }
}
