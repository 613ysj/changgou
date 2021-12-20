package com.changgou;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/9/25 16:29
 * @description 标题
 * @package com.changgou
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableCanalClient//启用canal的监听
@EnableFeignClients(basePackages = "com.changgou.content.feign")//启用feign的注解生效 basePackages 指定feig接口所在的包路基
public class CanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class,args);
    }
}
