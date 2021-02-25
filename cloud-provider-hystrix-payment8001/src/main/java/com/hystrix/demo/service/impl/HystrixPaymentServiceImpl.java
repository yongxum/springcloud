package com.hystrix.demo.service.impl;

import cn.hutool.core.util.IdUtil;
import com.hystrix.demo.dao.HystrixPaymentMapper;
import com.hystrix.demo.service.HystrixPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.commons.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class HystrixPaymentServiceImpl implements HystrixPaymentService {

    @Resource
    private HystrixPaymentMapper hystrixPaymentMapper;

    @Override
    public Payment findHystrixPaymentById(Long id) {
        return hystrixPaymentMapper.findHystrixPaymentById(id);
    }

    @Override
    public String paymentInfo_OK(Long id){
        return "线程池: " + Thread.currentThread().getName() + "  paymentInfo_OK, id: " + id + "\t" + "O" +
                "(∩_∩)O哈哈~";
    }

    // -------------服务降级

    @Override
    // 这个注解不管是超时, 还是报错, 都会走到paymentInfo_TimeOutHandler方法
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            // 设置线程超时时间, 如果超过3秒, 则走paymentInfo_TimeOutHandler方法
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Long id){
//        int age = 10 / 0;
        try{
//            TimeUnit.SECONDS.sleep(3);
            Thread.sleep(5000);
        }catch (Exception e){
            e.getMessage();
        }
        return "线程池: " + Thread.currentThread().getName() + "  paymentInfo_TimeOut, id: " + id + "\t" + "O" +
                "(∩_∩)O哈哈~" + "耗时3秒";
    }

    public String paymentInfo_TimeOutHandler(Long id){
        return "线程池: " + Thread.currentThread().getName() + "  paymentInfo_TimeOutHandler, id: " + id + "\t" + "O" +
                "o(╥﹏╥)o";
    }


    // ----------服务熔断
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreake_fallback", commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间范围
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value = "60"), // 失败率达到多少后跳闸
    })
    public String paymentCircuitBreake(@PathVariable("id") Long id){
        if(id < 0){
            throw new RuntimeException("*********** id 不能为负数 *******");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功, 流水号: " + serialNumber;
    }

    public String paymentCircuitBreake_fallback(@PathVariable("id") Long id){
        return "id 不能为负数, 请稍后再试, o(╥﹏╥)o  id : " + id;
    }

}
