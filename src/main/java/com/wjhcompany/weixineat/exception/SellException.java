package com.wjhcompany.weixineat.exception;

import com.wjhcompany.weixineat.enums.ResultEnum;

/**
 * @author JH Wei
 * @date 2019/3/18-10:21
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;
    }
}
