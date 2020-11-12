package com.cloud.demo.dao;

import com.springcloud.commons.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PaymentDao {
    public int insert(Payment payment);
    public Payment getPaymentById(Long id);
}
