package com.hystrix.demo.service.impl;

import com.hystrix.demo.dao.HystrixPaymentMapper;
import com.hystrix.demo.service.HystrixPaymentService;
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
    public String paymentInfo_TimeOut(Long id){
        try{
//            TimeUnit.SECONDS.sleep(3);
            Thread.sleep(3000);
        }catch (Exception e){
            e.getMessage();
        }
        return "线程池: " + Thread.currentThread().getName() + "  paymentInfo_TimeOut, id: " + id + "\t" + "O" +
                "(∩_∩)O哈哈~" + "耗时3秒";
    }

}
