package com.wjhcompany.weixineat.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @author JH Wei
 * @date 2019/3/13-21:05
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;

    private Integer code;
    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
