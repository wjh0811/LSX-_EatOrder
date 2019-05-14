package com.wjhcompany.weixineat.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wjhcompany.weixineat.dto.OrderDTO;
import com.wjhcompany.weixineat.entity.OrderDetail;
import com.wjhcompany.weixineat.enums.ResultEnum;
import com.wjhcompany.weixineat.exception.SellException;
import com.wjhcompany.weixineat.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JH Wei
 * @date 2019/4/2-10:12
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm)
    {   Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e)
        {
            log.error("对象转换错误",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }


        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
