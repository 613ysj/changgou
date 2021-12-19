package com.changgou;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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
public class CanalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class,args);
    }
}
