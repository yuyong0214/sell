package com.yong.order.controller;

import com.yong.order.client.ProductClient;
import com.yong.order.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
@Slf4j
public class ClientController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        // 第一种方式（直接使用RestTemplate 写死url）
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:81/msg", String.class);

        // 第二种方式（使用LoadBalancerClient获取url,再通过RestTemplate获取内容）
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String host = serviceInstance.getHost();
//        int port = serviceInstance.getPort();
//        String url = String.format("http://%s:%s", host, port)+"/msg";
//        String response = restTemplate.getForObject(url, String.class);

        // 第三种方式（使用@LoadBalanced注解，从而RestTemplate可以直接在url中使用服务名）
        // 值得注意的是第二种和第三种会进行负载均衡的策略
        //String response = restTemplate.getForObject("http://PRODUCT/msg", String.class);

        String response = productClient.productMsg();
        log.info("product msg is = " + response);
        return response;
    }

    @RequestMapping("/getProductList")
    public List<ProductInfo> getProductList() {
        System.out.println(123);
        List<ProductInfo> listForOrder = productClient.ListForOrder(Arrays.asList("157875196366160022", "157875227953464068"));
        log.info("list = "+listForOrder);
        return listForOrder;
    }
}
