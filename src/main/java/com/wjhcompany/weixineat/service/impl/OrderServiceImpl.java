package com.wjhcompany.weixineat.service.impl;

import com.wjhcompany.weixineat.converter.OrderMaster2OrderDTOConverter;
import com.wjhcompany.weixineat.dao.CategoryDao;
import com.wjhcompany.weixineat.dao.OrderDetailDao;
import com.wjhcompany.weixineat.dao.OrderMasterDao;
import com.wjhcompany.weixineat.dao.ProductInfoDao;
import com.wjhcompany.weixineat.dto.CartDTO;
import com.wjhcompany.weixineat.dto.OrderDTO;
import com.wjhcompany.weixineat.entity.OrderDetail;
import com.wjhcompany.weixineat.entity.OrderMaster;
import com.wjhcompany.weixineat.entity.ProductInfo;
import com.wjhcompany.weixineat.enums.OrderStatusEnum;
import com.wjhcompany.weixineat.enums.PayStatusEnum;
import com.wjhcompany.weixineat.enums.ResultEnum;
import com.wjhcompany.weixineat.exception.SellException;
import com.wjhcompany.weixineat.service.OrderService;
import com.wjhcompany.weixineat.service.ProductService;
import com.wjhcompany.weixineat.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import sun.rmi.runtime.Log;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JH Wei
 * @date 2019/3/18-9:59
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);
        //1.查询商品（数量、价格）
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (OrderDetail orderDetail: orderDetailList)
        {
            ProductInfo productInfoOne = productService.findOne(orderDetail.getProductId());
            if (productInfoOne == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价(单价、图片只能从商品表中查出，从前端返回的数据中没有，我们查出来设为productInfoOne，可以查详细信息)
            orderAmount=productInfoOne.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity()))
                        .add(orderAmount);




            //3.1写入订单详情数据库

            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfoOne,orderDetail);

            orderDetailDao.save(orderDetail);
            //4.1扣库存
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);

        }


        //3.2写入主订单数据库（orderMaster与orderDetail）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderAmount(orderAmount);
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderDTO.setOrderId(orderId);

        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterDao.save(orderMaster);

       //4.2扣库存
        productService.decreaseStock(cartDTOList);


        return orderDTO;
    }

    @Override
    public OrderDTO findone(String orderId) {
        OrderMaster orderMaster = orderMasterDao.findByOrderId(orderId);
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList))
        {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage ;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        //判断订单状态,必须是新创建的订单才可以取消

        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
        {
            log.error("订单状态不正确");
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);

        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult==null)
        {
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList()))
        {
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        //返回库存
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderDTO.getOrderId());
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (OrderDetail orderDetail:orderDetailList)
        {
            CartDTO cartDTO = new CartDTO();
            BeanUtils.copyProperties(orderDetail,cartDTO);
            cartDTOList.add(cartDTO);
        }


        productService.increaseStock(cartDTOList);
        //如果已经支付需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO

        }

        return orderDTO;

    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);

        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult==null){
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);

        }

        return orderDTO;



    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode()))
        {
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode()))
        {
            throw new SellException(ResultEnum.ORDER_PAY_ERROR);
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderMasterDao.save(orderMaster);
        OrderDTO result = OrderMaster2OrderDTOConverter.convert(orderMaster);
        if (result == null)
        {
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return result;
    }

    @Override
    public Page<OrderDTO> findListAll(Pageable pageable) {
        Page<OrderMaster> orderMasterAll = orderMasterDao.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterAll.getContent());

        return new PageImpl<>(orderDTOList,pageable,orderMasterAll.getTotalElements());
    }
    
    public  void jiaoHuanTest(){
        Integer[] arr1 = {10,11,12,13,14};
        jiaohuan(arr1,1,3);
        System.out.println(arr1.toString());

    }

    private  <T> void jiaohuan(T[] arr1, int i, int j) {
        T temp = arr1[i];
        arr1[i] = arr1[j];
        arr1[j] = temp;


        
    }
}
