package com.springcloud.comms.service;

import com.springcloud.commons.entities.Payment;
import com.springcloud.commons.utils.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
// 找eureks服务上需要调用那个微服务的服务名称
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    // 这里可以直接调用到service, 也可以直接调用到controller
//    CommonResult<Payment> getPaymentById(Long id);
    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);
}
