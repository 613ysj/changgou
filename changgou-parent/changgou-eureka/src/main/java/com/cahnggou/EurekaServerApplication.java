package com.cahnggou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: saijie
 * @date: 2021/11/30 19:18
 */
@SpringBootApplication
@EnableEurekaServer//开启eureka的服务端
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
