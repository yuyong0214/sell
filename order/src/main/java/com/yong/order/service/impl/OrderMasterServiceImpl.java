package com.yong.order.service.impl;

import com.yong.order.dataobject.OrderDetail;
import com.yong.order.dataobject.OrderMaster;
import com.yong.order.dto.OrderDTO;
import com.yong.order.enums.OrderStatusEnum;
import com.yong.order.enums.PayStatusEnum;
import com.yong.order.repository.OrderDetailRepository;
import com.yong.order.repository.OrderMasterRepository;
import com.yong.order.repository.utils.KeyUtil;
import com.yong.order.service.OrderMasterService;
import com.yong.product.client.ProductClient;
import com.yong.product.common.DecreaseStockInput;
import com.yong.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired(required = false)
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        // 查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoOutputList = productClient.listForOrder(productIdList);
        // 计算总价
        BigDecimal orderAmount = BigDecimal.ZERO;
        for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
            for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
                if (productInfoOutput.getProductId().equals(orderDetail.getProductId())) {
                    orderAmount = productInfoOutput.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfoOutput, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.getUniqueKey());

                    // 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        // 扣减库存
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);
        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setCreateTime(new Date());
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setUpdateTime(new Date());
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
