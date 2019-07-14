package com.yong.order.repository;

import com.yong.order.OrderApplicationTests;
import com.yong.order.dataobject.OrderDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void testSave() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCreateTime(new Date());
        orderDetail.setDetailId("123456");
        orderDetail.setOrderId("123");
        orderDetail.setProductIcon("");
        orderDetail.setProductId("21");
        orderDetail.setProductName("222");
        orderDetail.setProductQuantity(2);
        orderDetail.setProductPrice(new BigDecimal(123.3));
        orderDetail.setUpdateTime(new Date());
        orderDetailRepository.save(orderDetail);
    }
}