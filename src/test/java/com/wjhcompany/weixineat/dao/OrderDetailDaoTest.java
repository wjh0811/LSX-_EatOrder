package com.wjhcompany.weixineat.dao;

import com.wjhcompany.weixineat.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author JH Wei
 * @date 2019/3/18-9:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail("201903181001","12334567","155","鸡腿",new BigDecimal(3.4),1,"www.l");
        OrderDetail save = orderDetailDao.save(orderDetail);
        Assert.assertNotEquals(null,save);

    }

    @Test
    public void findByOrderId() {

        List<OrderDetail> result = orderDetailDao.findByOrderId("12334567");
        Assert.assertNotEquals(null,result);
    }
}