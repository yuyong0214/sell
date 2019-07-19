package com.yong.product.server.impl;

import com.yong.product.common.DecreaseStockInput;
import com.yong.product.dataobject.ProductInfo;
import com.yong.product.enums.ProductStatusEnum;
import com.yong.product.enums.ResultEnum;
import com.yong.product.exception.ProductExcption;
import com.yong.product.repository.ProductInfoRepository;
import com.yong.product.server.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        decreaseStockProcess(decreaseStockInputList);
        // TODO 发送mq消息
    }

    /**
     * 扣减库存
     *
     * @param decreaseStockInputList
     * @return
     */
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput : decreaseStockInputList) {
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(decreaseStockInput.getProductId());
            // 判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductExcption(ResultEnum.PRODUCT_NOT_EXIST);
            }

            ProductInfo productInfo = productInfoOptional.get();
            // 库存是否足够
            Integer result = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (result < 0) {
                throw new ProductExcption(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
