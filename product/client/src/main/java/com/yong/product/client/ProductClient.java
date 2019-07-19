package com.yong.product.client;

import com.yong.product.common.DecreaseStockInput;
import com.yong.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: YUY
 * @date: 2019/7/19 16:16
 */
@FeignClient(name = "product")
public interface ProductClient {

    @RequestMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    @RequestMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
