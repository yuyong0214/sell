package com.yong.product.exception;

import com.yong.product.enums.ResultEnum;

/**
 * @author: YUY
 * @date: 2019/7/19 18:25
 */
public class ProductExcption extends RuntimeException {
    private Integer code;

    public ProductExcption(Integer code,String message){
        super(message);
        this.code = code;
    }

    public ProductExcption(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
