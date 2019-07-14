package com.yong.product.server.impl;

import com.yong.product.dataobject.ProductInfo;
import com.yong.product.enums.ProductStatusEnum;
import com.yong.product.repository.ProductInfoRepository;
import com.yong.product.server.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public List<ProductInfo> findUpAll() {
        List<ProductInfo> byProductStatus = productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
        return byProductStatus;
    }

    @Override
    public List<ProductInfo> findByProductId(List<String> productId) {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductIdIn(productId);
        return productInfoList;
    }
}
