package com.hystrix.demo.service.impl;

import com.hystrix.demo.dao.HystrixPaymentMapper;
import com.hystrix.demo.service.HystrixPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.commons.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

}
