package com.kyt.springclooud.controller;

import com.kyt.springcloud.entities.CommonResult;
import com.kyt.springcloud.entities.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author kyt
 * @create 2021--03--18--20:21
 */
@RestController
public class ConsumerController {

    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/create")
    public ResponseEntity<CommonResult> create(Payment payment){
        return restTemplate.postForEntity(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/get/{id}")
    public ResponseEntity<CommonResult> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}
