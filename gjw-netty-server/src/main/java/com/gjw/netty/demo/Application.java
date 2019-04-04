package com.gjw.netty.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Repository;

/**
 * @author: gaojunwei
 * @Date: 2018/11/13
 * @Description:
 */
/*@Configuration
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan*/
@SpringBootApplication
@MapperScan("com.gjw.demo.dao.mapper")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
