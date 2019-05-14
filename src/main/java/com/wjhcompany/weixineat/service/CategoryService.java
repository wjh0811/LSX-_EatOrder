package com.wjhcompany.weixineat.service;

import com.wjhcompany.weixineat.entity.ProductCategory;

import java.util.List;

/**
 * @author JH Wei
 * @date 2019/3/13-16:56
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
