package com.cloud.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.demo.dao.PaymentDao;
import com.cloud.demo.service.PaymentService;
import com.springcloud.commons.entities.BaseClass;
import com.springcloud.commons.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public void test(String json, Class<T> t){
        try {
            JSONObject jsonObject = JSONObject.parseObject(json);
            JSON.toJavaObject(jsonObject, t);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
