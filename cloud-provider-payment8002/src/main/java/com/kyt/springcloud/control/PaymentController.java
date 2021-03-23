package com.kyt.springcloud.control;

import com.kyt.springcloud.entities.CommonResult;
import com.kyt.springcloud.entities.Payment;
import com.kyt.springcloud.serivce.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author kyt
 * @create 2021--03--18--10:09
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String  serverPort;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("插入结果****"+result);
        if(result>0){
            return new CommonResult(200,"插入成功,serverPort"+serverPort,result);
        }else{
            return new CommonResult(404,"插入失败");
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果****"+payment+"hehehehehe");
        if(payment!=null){
            return new CommonResult(200,"查询结果,serverPort"+serverPort,payment);
        }else{
            return new CommonResult(404,"查询失败");
        }
    }
}
