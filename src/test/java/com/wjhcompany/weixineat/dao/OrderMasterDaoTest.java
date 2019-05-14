package com.wjhcompany.weixineat.dao;

import com.wjhcompany.weixineat.entity.OrderMaster;
import com.wjhcompany.weixineat.enums.OrderStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author JH Wei
 * @date 2019/3/18-8:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    private final String OPENID = "12266";
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setBuyerOpenid("12266");
        orderMaster.setBuyerName("tom");
        orderMaster.setOrderId("1886666");
        orderMaster.setBuyerPhone("18888888888");
        orderMaster.setBuyerAddress("beijing");
        orderMaster.setOrderAmount(new BigDecimal(8.9));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        OrderMaster orderMaster1 = orderMasterDao.save(orderMaster);
        Assert.assertNotEquals(null,orderMaster1);


    }

    @Test
    public void findByBuyerOpenidTest() {
        PageRequest request = PageRequest.of(0,1);
        Page<OrderMaster> result = orderMasterDao.findByBuyerOpenid(OPENID, request);
        System.out.println(result.getContent());

    }

    @Test
    public void findByOrderIdTest(){
        OrderMaster byOrderId = orderMasterDao.findByOrderId("188");
        Assert.assertNotEquals(null,byOrderId);

    }
}