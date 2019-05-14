package com.wjhcompany.weixineat.controller;

import com.wjhcompany.weixineat.VO.ResultVo;
import com.wjhcompany.weixineat.converter.OrderForm2OrderDTOConverter;
import com.wjhcompany.weixineat.dto.OrderDTO;
import com.wjhcompany.weixineat.dto.UserDTO;
import com.wjhcompany.weixineat.enums.ResultEnum;
import com.wjhcompany.weixineat.exception.SellException;
import com.wjhcompany.weixineat.form.OrderForm;
import com.wjhcompany.weixineat.service.BuyerService;
import com.wjhcompany.weixineat.service.OrderService;
import com.wjhcompany.weixineat.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JH Wei
 * @date 2019/4/2-9:36
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
@Api("swagger ui --- 买家订单控制器")
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    //创建订单
    @ApiOperation("swagger ui---创建订单")
    @PostMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("创建订单参数不正确",orderForm);
            //测试，试试git
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);



    }
    //订单列表
    @ApiOperation("swagger ui---订单列表")
    @GetMapping("/orderlist")
    public ResultVo<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam("page") Integer page,
                                         @RequestParam("size") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = PageRequest.of(page,size);
        Page<OrderDTO> orderDTOpage = orderService.findList(openid,request);
        return ResultVOUtil.success(orderDTOpage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    @ApiOperation("swagger ui---订单详情")
    public ResultVo<List<OrderDTO>> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderid") String orderId){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        orderDTOList.add(orderDTO);
        orderDTOList.add(orderDTO);
        //第二次使用git提交
        return ResultVOUtil.success(orderDTOList);
    }
    //取消订单
    @PostMapping("/cancel")
    @ApiOperation("swagger ui---取消订单")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }
    //接口测试
    @GetMapping("/test")
    @ApiOperation("swagger ui---接口测试")
    public Map<String,Object> test(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","王尼玛");
        List<UserDTO> mapList = new ArrayList<>();
        UserDTO userDTO = new UserDTO("小王","7");
        mapList.add(userDTO);
        UserDTO userDTO1 = new UserDTO("小尼玛","10");
        mapList.add(userDTO1);
        map.put("fans",mapList);
        return map;
    }


}
