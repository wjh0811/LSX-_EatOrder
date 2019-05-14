package com.wjhcompany.weixineat.service.impl;

import com.wjhcompany.weixineat.dto.OrderDTO;
import com.wjhcompany.weixineat.enums.ResultEnum;
import com.wjhcompany.weixineat.exception.SellException;
import com.wjhcompany.weixineat.service.BuyerService;
import com.wjhcompany.weixineat.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JH Wei
 * @date 2019/4/3-8:38
 */
@Service
@Slf4j
@Transactional
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
    return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null){
            log.error("差不多该订单");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        OrderDTO result = orderService.cancel(orderDTO);
        return result;
    }
    private OrderDTO checkOrderOwner(String openid,String orderId){
        OrderDTO orderDTO = orderService.findone(orderId);
        if (orderDTO == null){
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid))
        {
            log.error("查询订单——订单的openID不一致");
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
