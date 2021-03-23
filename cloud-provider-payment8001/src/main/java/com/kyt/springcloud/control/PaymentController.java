package com.kyt.springcloud.control;

import com.kyt.springcloud.entities.CommonResult;
import com.kyt.springcloud.entities.Payment;
import com.kyt.springcloud.serivce.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for(String elements:services){
            log.info("****elements"+elements);
        }
        List<ServiceInstance> discoveryClientInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : discoveryClientInstances){
            log.info(instance+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
}
