package com.springcloud.comms.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Feign的日志级别
 * NONE: 默认的, 不显示任何日志
 * BASIC: 仅记录请求方法, URL, 响应码及执行时间
 * HEADERS: 除了BASIC中定义的信息之外, 还有请求和相应的头系想你;
 * FULL: 除了HEADERS中定义的信息之外, 还有请求和相应的正文及元数据
 */

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

}
