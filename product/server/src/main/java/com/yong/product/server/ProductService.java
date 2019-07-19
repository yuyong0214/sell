package com.yong.product.server;

import com.yong.product.common.DecreaseStockInput;
import com.yong.product.dataobject.ProductInfo;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productId
     * @return
     */
    List<ProductInfo> findByProductId(List<String> productId);

    /**
     * 扣减库存
     * @param decreaseStockInputList
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
