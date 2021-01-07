package com.cloud.demo.service;

import com.springcloud.commons.entities.Payment;
import org.apache.poi.ss.formula.functions.T;

public interface PaymentService {
    public int insert(Payment payment);
    public Payment getPaymentById(Long id);
    public void test(String json, Class<T> t);
}
