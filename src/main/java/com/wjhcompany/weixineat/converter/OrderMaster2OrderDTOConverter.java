package com.wjhcompany.weixineat.converter;

import com.wjhcompany.weixineat.dto.OrderDTO;
import com.wjhcompany.weixineat.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JH Wei
 * @date 2019/4/1-22:17
 */
public class OrderMaster2OrderDTOConverter {
    public static OrderDTO convert(OrderMaster orderMaster)
    {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList)
    {   List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderMaster orderMaster:orderMasterList){
            OrderDTO orderDTO = OrderMaster2OrderDTOConverter.convert(orderMaster);
            orderDTOList.add(orderDTO);


        }
        return orderDTOList;
    }
}
