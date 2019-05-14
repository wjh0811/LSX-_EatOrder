package com.wjhcompany.weixineat.controller;

import com.wjhcompany.weixineat.dto.OrderDTO;
import com.wjhcompany.weixineat.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author JH Wei
 * @date 2019/4/3-15:13
 */
@Controller
@RequestMapping("/seller/order")
@Api("swagger ui --- 卖家信息控制器")
public class SellOrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/list")
    @ApiOperation("swagger ui---查询所有的订单")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size,
                             Map<String,Object> map){
        PageRequest pageRequest = PageRequest.of(page - 1,size);
        Page<OrderDTO> listAll = orderService.findListAll(pageRequest);
        map.put("orderDTOPage",listAll);
        return new ModelAndView("order/list",map);


    }

}
