package com.wjhcompany.weixineat.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.wjhcompany.weixineat.dto.OrderDTO;
import com.wjhcompany.weixineat.dto.UserDTO;
import com.wjhcompany.weixineat.entity.OrderDetail;
import com.wjhcompany.weixineat.enums.OrderStatusEnum;
import com.wjhcompany.weixineat.enums.PayStatusEnum;
import com.wjhcompany.weixineat.testUnit.ALiPay;
import com.wjhcompany.weixineat.testUnit.MeiTuan;
import com.wjhcompany.weixineat.testUnit.WeChat;
import javassist.expr.NewArray;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.nio.file.Watchable;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author JH Wei
 * @date 2019/3/18-10:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYEROPENID = "11018888";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName("marry");
        orderDTO.setBuyerAddress("shandong");
        orderDTO.setBuyerPhone("166666666666");
        orderDTO.setBuyerOpenid(BUYEROPENID);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductName("鸭脖");
        orderDetail.setProductQuantity(11);
        orderDetailList.add(orderDetail);
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setProductName("鸡柳");
        orderDetail2.setProductQuantity(12);
        orderDetailList.add(orderDetail2);

        orderDTO.setOrderDetailList(orderDetailList);
        System.out.println(orderDTO.toString());
        orderService.create(orderDTO);

    }

    @Test
    public void findone() {
        OrderDTO orderDTO = orderService.findone("188");
        Assert.assertNotEquals(null,orderDTO);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList("166", pageRequest);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());

    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findone("188");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());

    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findone("188");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertNotEquals(OrderStatusEnum.NEW.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
       OrderDTO orderDTO = orderService.findone("188");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertNotEquals(PayStatusEnum.WAIT.getCode(),result.getPayStatus());
    }
    @Test
    public void findAll(){
        PageRequest pageRequest = PageRequest.of(0,2);
        Page<OrderDTO> listAll = orderService.findListAll(pageRequest);
        Assert.assertEquals(3,listAll.getTotalElements());
           /*  BigDecimal aa = new BigDecimal("1.5");
        BigDecimal cc = new BigDecimal("0.6");

        int n = 0;
        BigDecimal k = new BigDecimal("0.0");
        BigDecimal hh = new BigDecimal("0.0");
        for (int i = 1;i<134;i++){
                for (int z = 1;z<335;z++){

                     k = (aa.multiply(new BigDecimal(i)).add(cc.multiply(new BigDecimal(z))).subtract(aa.multiply(new BigDecimal(i)).add(cc.multiply(new BigDecimal(z))).setScale(0,BigDecimal.ROUND_DOWN))).setScale(1,BigDecimal.ROUND_HALF_DOWN);
                     hh = aa.multiply(new BigDecimal(i)).add(cc.multiply(new BigDecimal(z)));
                    if(k.compareTo(new BigDecimal("0.9"))==0&&hh.compareTo(new BigDecimal(201.9)) < 0){
                        n++;
                        System.out.println("符合要求"+n+"次");
                    }
                }

        }*/
    }
    @Test
    public void jiaoHuan() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        orderService.jiaoHuanTest();
        Map<String,Object> map = new HashMap<>();
        map.put("name","王尼玛");
        List<UserDTO> mapList = new ArrayList<>();
        UserDTO userDTO = new UserDTO("小王","7");
        mapList.add(userDTO);
        UserDTO userDTO1 = new UserDTO("小尼玛","10");
        mapList.add(userDTO1);
        map.put("fans",mapList);
        System.out.println(map);



    }

    public static  void pay(MeiTuan aLiPay){
        //aLiPay.payOnLine();

    }
}