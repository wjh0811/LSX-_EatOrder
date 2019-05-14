package com.wjhcompany.weixineat.service;

import com.wjhcompany.weixineat.dto.OrderDTO;

/**
 * @author JH Wei
 * @date 2019/4/3-8:35
 */
public interface BuyerService {
    OrderDTO findOrderOne(String openid, String orderId);
    OrderDTO cancelOrder(String openid,String orderId);
}
