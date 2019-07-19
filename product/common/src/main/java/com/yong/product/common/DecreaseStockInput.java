package com.yong.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 减库存入参
 *
 * @author: YUY
 * @date: 2019/7/19 16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DecreaseStockInput {
    private String productId;

    private Integer productQuantity;
}
