package com.feignHystrix.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface ConsumerOpenFeignHystrixService {

    @GetMapping("/hystrixPayment/paymentInfo_OK/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id);

    @GetMapping("/hystrixPayment/paymentInfo_TimeOut/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id);

}
