package com.cloud.demo.controller;

import com.cloud.demo.service.PaymentService;
import com.springcloud.commons.entities.Payment;
import com.springcloud.commons.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/insert")
    public CommonResult insert(@RequestBody Payment payment){
        int insert = paymentService.insert(payment);
        log.info("**********插入结果: {}", insert);
        if(insert > 0){
            return new CommonResult(200, "插入数据库成功, serverPort: " + serverPort);
        }else{
            return new CommonResult(400, "插入数据失败");
        }
    }

    @GetMapping("/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果: {}", payment);
        return  new CommonResult(200, "查询成功, serverPort: " + serverPort,
                payment);
    }

}
