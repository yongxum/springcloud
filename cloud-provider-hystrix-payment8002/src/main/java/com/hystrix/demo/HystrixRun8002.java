package com.hystrix.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HystrixRun8002 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixRun8002.class, args);
    }
}
