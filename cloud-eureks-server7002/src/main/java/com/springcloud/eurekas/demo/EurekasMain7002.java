package com.springcloud.eurekas.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekasMain7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekasMain7002.class, args);
    }
}
