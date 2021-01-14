package com.feignHystrix.demo.service;

import com.feignHystrix.demo.service.impl.ConsumerOpenFeignHystrixServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
// 调用CLOUD-PROVIDER-HYSTRIX-PAYMENT微服务下的所有方法, 如果出现问题, 则根据fallback配置查找到该类, 通过该类处理错误
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = ConsumerOpenFeignHystrixServiceImpl.class)
//@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface ConsumerOpenFeignHystrixService {

    @GetMapping("/hystrixPayment/paymentInfo_OK/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id);

    @GetMapping("/hystrixPayment/paymentInfo_TimeOut/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id);

}
