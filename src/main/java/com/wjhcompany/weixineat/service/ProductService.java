package com.wjhcompany.weixineat.service;

import com.wjhcompany.weixineat.dto.CartDTO;
import com.wjhcompany.weixineat.entity.ProductInfo;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @author JH Wei
 * @date 2019/3/13-20:52
 */
public interface ProductService {

    /*查询在架的商品列表*/
    List<ProductInfo> findUpAll();

    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存；
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存；
    void decreaseStock(List<CartDTO> cartDTOList);




}
