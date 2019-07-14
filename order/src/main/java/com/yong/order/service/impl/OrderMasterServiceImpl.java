package com.yong.order.service.impl;

import com.yong.order.dataobject.OrderMaster;
import com.yong.order.dto.OrderDTO;
import com.yong.order.repository.OrderDetailRepository;
import com.yong.order.repository.OrderMasterRepository;
import com.yong.order.repository.utils.KeyUtil;
import com.yong.order.service.OrderMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

        // 查询商品信息（调用商品服务）
        // 计算总价
        // 扣减库存
        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.geiUniqueKey());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(3));
        orderMaster.setPayStatus(0);
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
