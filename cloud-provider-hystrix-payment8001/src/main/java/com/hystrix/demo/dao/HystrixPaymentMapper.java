package com.hystrix.demo.dao;

import com.springcloud.commons.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HystrixPaymentMapper {

    public Payment findHystrixPaymentById(Long id);
}
