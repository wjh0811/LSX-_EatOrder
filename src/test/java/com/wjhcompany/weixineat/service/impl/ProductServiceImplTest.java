package com.wjhcompany.weixineat.service.impl;

import com.wjhcompany.weixineat.entity.ProductInfo;
import com.wjhcompany.weixineat.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author JH Wei
 * @date 2019/3/13-21:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> infoList = productService.findUpAll();
        Assert.assertNotEquals(0,infoList.size());

    }

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("122");
        Assert.assertNotEquals(null,productInfo);



    }

    @Test
    public void findAll() throws Exception {


        PageRequest request = PageRequest.of(0,4);

        Page<ProductInfo> all = productService.findAll(request);
        System.out.println(all.getContent());
        System.out.println("==================================================");
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo("1234","鸡柳",new BigDecimal(2.3),100,"好吃","www.baihe.com", ProductStatusEnum.UP.getCode(),  3);
        ProductInfo info = productService.save(productInfo);
        Assert.assertNotEquals(null,info);
    }
}