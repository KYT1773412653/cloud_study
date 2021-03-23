package com.kyt.springcloud.serivce;

import com.kyt.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;


/**
 * @author kyt
 * @create 2021--03--18--10:04
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);


}
