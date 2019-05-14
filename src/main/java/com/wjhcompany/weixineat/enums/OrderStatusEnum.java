package com.wjhcompany.weixineat.enums;

import lombok.Getter;

/**
 * @author JH Wei
 * @date 2019/3/15-18:10
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"已经完成"),
    FINISHED(1,"正在进行"),
    CANCEL(2,"订单取消") ;

    private Integer code;
    private String  msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
