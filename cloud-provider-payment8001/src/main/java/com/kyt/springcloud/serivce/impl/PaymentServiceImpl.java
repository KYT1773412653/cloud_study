package com.kyt.springcloud.serivce.impl;

import com.kyt.springcloud.dao.PaymentDao;
import com.kyt.springcloud.entities.Payment;
import com.kyt.springcloud.serivce.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kyt
 * @create 2021--03--18--10:06
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
