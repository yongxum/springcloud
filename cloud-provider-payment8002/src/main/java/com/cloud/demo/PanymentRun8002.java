package com.cloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PanymentRun8002 {
    public static void main(String[] args) {
        SpringApplication.run(PanymentRun8002.class, args);
    }
}
