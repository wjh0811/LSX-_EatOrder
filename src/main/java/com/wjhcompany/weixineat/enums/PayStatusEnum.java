package com.wjhcompany.weixineat.enums;

import lombok.Getter;

/**
 * @author JH Wei
 * @date 2019/3/15-18:17
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),SUCCESS(1,"支付成功");


    private Integer code;
    private String  msg;

    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
