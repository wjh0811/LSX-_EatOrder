package com.wjhcompany.weixineat.dao;

import com.wjhcompany.weixineat.entity.ProductInfo;
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
 * @date 2019/3/13-17:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Test
    public void saveTest(){

        ProductInfo productInfo = new ProductInfo("123456","皮蛋粥",new BigDecimal(3.2),100,"好喝的粥","www.baidu.com",0,3);
        ProductInfo info = productInfoDao.save(productInfo);
        Assert.assertNotEquals(null,info);


    }


    @Test
    public void findByProductStatusTest() {

        List<ProductInfo> productInfoList = productInfoDao.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }
}