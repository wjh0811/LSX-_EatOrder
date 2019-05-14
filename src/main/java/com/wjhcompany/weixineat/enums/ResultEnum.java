package com.wjhcompany.weixineat.enums;

import lombok.Getter;

/**
 * @author JH Wei
 * @date 2019/3/18-10:22
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不足"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单错误"),
    ORDER_UPDATE_FAIL(15,"更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_ERROR(17,"订单支付失败"),
    CART_EMPTY(18,"购物车为空"),
    ORDER_OWNER_ERROR(10,"该订单不属于当前用户")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
