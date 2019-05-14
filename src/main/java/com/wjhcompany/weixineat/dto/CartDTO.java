package com.wjhcompany.weixineat.dto;

import lombok.Data;

/**
 * @author JH Wei
 * @date 2019/3/18-19:36
 */
@Data
public class CartDTO {

    private String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO() {

    }
}
