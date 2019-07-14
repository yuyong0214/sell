package com.yong.order.client;

import com.yong.order.dataobject.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("product")
public interface ProductClient {
    @GetMapping("/msg")
    String productMsg();

    @RequestMapping("/product/listForOrder")
    List<ProductInfo> ListForOrder(@RequestBody List<String> productId);
}
