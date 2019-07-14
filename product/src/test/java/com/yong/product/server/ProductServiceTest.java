package com.yong.product.server;

import com.yong.product.dataobject.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

    @Autowired(required = false)
    private ProductService productService;
    @Test
    public void findUpAll() {
        List<ProductInfo> upAll = productService.findUpAll();
        for (ProductInfo productInfo : upAll) {
            System.out.println(productInfo);
        }
    }
}