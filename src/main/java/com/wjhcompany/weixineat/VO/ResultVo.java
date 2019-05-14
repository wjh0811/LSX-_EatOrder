package com.wjhcompany.weixineat.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author JH Wei
 * @date 2019/3/13-20:12
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo<E> {

    /*错误码*/
    private Integer code;

    /*提示信息*/
    private String  msg;

    /**/
    private E      data;
}
