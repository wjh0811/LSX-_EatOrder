package com.wjhcompany.weixineat.controller;

import com.wjhcompany.weixineat.VO.ProductInfoVo;
import com.wjhcompany.weixineat.VO.ProductVo;
import com.wjhcompany.weixineat.VO.ResultVo;
import com.wjhcompany.weixineat.entity.ProductCategory;
import com.wjhcompany.weixineat.entity.ProductInfo;
import com.wjhcompany.weixineat.service.CategoryService;
import com.wjhcompany.weixineat.service.impl.CategoryServiceImpl;
import com.wjhcompany.weixineat.service.impl.ProductServiceImpl;
import com.wjhcompany.weixineat.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JH Wei
 * @date 2019/3/13-19:54
 */
@RestController
@RequestMapping("/buyer/product")
@Api("swagger ui --- 商品信息控制器")
public class BuyerProductController {

    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/list")
    @ApiOperation("swagger ui---查询所有上架的商品")
    public ResultVo list(){

        //1.查询所有上架的商品
        List<ProductInfo> upAll = productService.findUpAll();

        //2.查询类目（一次性查询）
        List<Integer> list = new ArrayList<Integer>();
        for (ProductInfo productInfo:upAll){
            list.add(productInfo.getCategoryType());
        }
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(list);
        //3.数据的拼装

        List<ProductVo> productVoList = new ArrayList<>();
         for (ProductCategory productCategory : productCategoryList)
         {
             ProductVo productVo = new ProductVo();
             productVo.setCategoryName(productCategory.getCategoryName());
             productVo.setCategoryType(productCategory.getCategoryType());

             List<ProductInfoVo> productInfoVoList = new ArrayList<>();
             for (ProductInfo productInfo : upAll)
             {
                 if(productCategory.getCategoryType().equals(productInfo.getCategoryType()))
                 {
                     ProductInfoVo productInfoVo = new ProductInfoVo();
                     BeanUtils.copyProperties(productInfo,productInfoVo);
                     productInfoVoList.add(productInfoVo);

                 }

             }
             productVo.setProductInfoVoList(productInfoVoList);
             productVoList.add(productVo);
         }

        return ResultVOUtil.success(productVoList);

    }
}
