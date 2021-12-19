package com.changgou;

import entity.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author: saijie
 * @date: 2021/11/30 19:46
 */
@SpringBootApplication
@EnableEurekaClient
//扫描指定的包下的所有的接口 为接口产生代理对象交给spring容器 mapper扫描
//@MapperScan(basePackages = "com.changgou.goods.dao")
//通用的mapper提供的mapper扫描注解
@MapperScan(basePackages = "com.changgou.goods.dao")
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

    @Bean
    //IdWork直接交给spring容器管理 如果要使用直接注入
    public IdWorker idWorker(){
        return new IdWorker(0,0);
    }

}
