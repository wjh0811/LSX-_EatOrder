package com.wjhcompany.weixineat.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wjhcompany.weixineat.entity.OrderDetail;
import com.wjhcompany.weixineat.enums.OrderStatusEnum;
import com.wjhcompany.weixineat.enums.PayStatusEnum;
import com.wjhcompany.weixineat.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author JH Wei
 * @date 2019/3/18-9:50
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String     orderId;

    private String     buyerName;

    private String     buyerPhone;

    private String     buyerAddress;

    private String     buyerOpenid;

    private BigDecimal orderAmount;

    private Integer    orderStatus;

    private Integer    payStatus;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date       updateTime;

    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerPhone='" + buyerPhone + '\'' +
                ", buyerAddress='" + buyerAddress + '\'' +
                ", buyerOpenid='" + buyerOpenid + '\'' +
                ", orderAmount=" + orderAmount +
                ", orderStatus=" + orderStatus +
                ", payStatus=" + payStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}
