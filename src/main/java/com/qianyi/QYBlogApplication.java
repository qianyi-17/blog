package com.qianyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 千亦
 * @create 2022-10-05-20:23
 */
@SpringBootApplication
@MapperScan("com.qianyi.mapper")
@EnableScheduling  //开启定时任务功能
@EnableSwagger2
public class QYBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(QYBlogApplication.class,args);
    }
}
