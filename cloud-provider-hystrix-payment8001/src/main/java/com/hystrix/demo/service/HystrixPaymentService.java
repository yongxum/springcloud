package com.hystrix.demo.service;

import com.springcloud.commons.entities.Payment;

public interface HystrixPaymentService {

    Payment findHystrixPaymentById(Long id);

    String paymentInfo_OK(Long id);

    String paymentInfo_TimeOut(Long id);

    String paymentCircuitBreake(Long id);
}
