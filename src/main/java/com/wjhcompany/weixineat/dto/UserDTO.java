package com.wjhcompany.weixineat.dto;

import lombok.Data;

/**
 * @author JH Wei
 * @date 2019/4/15-10:51
 */
@Data
public class UserDTO {
    private String name;
    private String age;

    public UserDTO(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
