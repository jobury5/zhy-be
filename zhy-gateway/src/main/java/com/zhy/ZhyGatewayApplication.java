package com.zhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: jobury
 * @Date: 2024/9/10 10:27
 */

@SpringBootApplication
@EnableDiscoveryClient
public class ZhyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhyGatewayApplication.class, args);
    }

}
