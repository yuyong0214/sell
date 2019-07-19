package com.yong.order.enums;

import lombok.Getter;

/**
 * @author: YUY
 * @date: 2019/7/19 15:47
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"取消"),
    ;
    private Integer code;
    private String message;
    OrderStatusEnum(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
