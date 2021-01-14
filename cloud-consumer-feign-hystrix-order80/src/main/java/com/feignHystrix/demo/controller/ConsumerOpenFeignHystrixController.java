package com.feignHystrix.demo.controller;

import com.feignHystrix.demo.service.ConsumerOpenFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/consumerOpenFeignHystrix")
// 如果没有特别指明降级方法, 则走通用处理方法
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class ConsumerOpenFeignHystrixController {

    @Autowired
    private ConsumerOpenFeignHystrixService consumerOpenFeignHystrixService;

    @GetMapping("/payment_OK/{id}")
    public String paymentOK(@PathVariable("id") Long id){
        String result = consumerOpenFeignHystrixService.paymentInfo_OK(id);
        log.info("result: {}", result);
        return result;
    }

    @GetMapping("/payment_TimeOut/{id}")
    // 这个注解不管是超时, 还是报错, 都会走到paymentInfo_TimeOutHandler方法
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            // 设置线程超时时间, 如果超过3秒, 则走paymentInfo_TimeOutHandler方法
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//    })
    @HystrixCommand     // 只配置标签, 则会走通用处理方法
    public String paymentTimeOut(@PathVariable("id") Long id){
        String result = consumerOpenFeignHystrixService.paymentInfo_TimeOut(id);
        log.info("result: {}", result);
        return result;
    }

    public String paymentInfo_TimeOutHandler(@PathVariable("id") Long id){
        return "我是消费者80, 对方支付系统繁忙, 请稍后再试, 或自己运行错误, 检查本系统, o(╥﹏╥)o";
    }

    // 通用fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息, 请稍后再试o(╥﹏╥)o";
    }

}
