package com.springcloud.comms.controller;

import com.springcloud.commons.entities.Payment;
import com.springcloud.commons.utils.CommonResult;
import com.springcloud.comms.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumer")
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/payment/feign/timeOut")
    public String paymentFeignTimeOutTest(){
        return paymentFeignService.paymentFeignTimeOutTest();
    }

}
