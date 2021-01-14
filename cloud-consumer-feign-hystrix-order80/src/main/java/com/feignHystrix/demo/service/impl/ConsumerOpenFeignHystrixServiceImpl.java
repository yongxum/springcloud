package com.feignHystrix.demo.service.impl;

import com.feignHystrix.demo.service.ConsumerOpenFeignHystrixService;
import org.springframework.stereotype.Component;

@Component
public class ConsumerOpenFeignHystrixServiceImpl implements ConsumerOpenFeignHystrixService {
    @Override
    public String paymentInfo_OK(Long id) {
        return "ConsumerOpenFeignHystrixServiceImpl_paymentInfo_OK, 实现服务降级, 可以处理报错异常, 超时异常, 宕机异常等";
    }

    @Override
    public String paymentInfo_TimeOut(Long id) {
        return "ConsumerOpenFeignHystrixServiceImpl_paymentInfo_TimeOut, 实现服务降级, 可以处理报错异常, 超时异常, 宕机异常等";
    }
}
