package com.cloud.demo.service;

import com.springcloud.commons.entities.Payment;

import java.util.List;

public interface PaymentService {
    public int insert(Payment payment);
    public Payment getPaymentById(Long id);
}
