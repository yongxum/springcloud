package com.hystrix.demo.controller;

import com.hystrix.demo.service.HystrixPaymentService;
import com.springcloud.commons.entities.Payment;
import com.springcloud.commons.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("hystrixPayment")
public class HystrixPaymentController {

    @Autowired
    private HystrixPaymentService hystrixPaymentService;

    @Value("${server.port}")
    private String serverPort;

    /**
     * 根据id查询支付信息
     * @param id
     * @return
     */
    @GetMapping("/findHystrixPaymentById/{id}")
    public CommonResult findHystrixPayment(@PathVariable("id") Long id){
        Payment payment = hystrixPaymentService.findHystrixPaymentById(id);
        log.info("查询结果: {}", payment);
        return  new CommonResult(200, "查询成功, serverPort: " + serverPort,
                payment);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/paymentInfo_OK/{id}")
    public String paymentInfo_OK(@PathVariable("id") Long id){
        String result = hystrixPaymentService.paymentInfo_OK(id);
        log.info("方法名: {} ********* result; {} ************", "paymentInfo_OK", result);
        return result;
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/paymentInfo_TimeOut/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Long id){
        String result = hystrixPaymentService.paymentInfo_TimeOut(id);
        log.info("方法名: {} ********* result; {} ************", "paymentInfo_TimeOut", result);
        return result;
    }

    // 服务熔断
    @GetMapping("/paymentCircuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Long id){
        String str = hystrixPaymentService.paymentCircuitBreake(id);
        log.info("***result***" + str);
        return str;
    }

}
