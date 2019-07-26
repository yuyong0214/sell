package com.yong.product.repository;

import com.yong.product.ProductApplicationTests;
import com.yong.product.dataobject.ProductInfo;
import org.junit.Test;
import org.springframework.stereotype.Component;

/**
 * @author: YUY
 * @date: 2019/7/25 17:20
 */
@Component
public class ProductTest extends ProductApplicationTests {
    @Test
    public void te(){
        ProductInfo product = new ProductInfo();
        System.out.println(product.getProductStock());
    }
}
