package com.wjhcompany.weixineat.service.impl;

import com.wjhcompany.weixineat.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author JH Wei
 * @date 2019/3/13-17:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory result = categoryService.findOne(1);
        System.out.println(result.toString());
        Assert.assertNotNull(result);

    }

    @Test
    public void findAll() {
        List<ProductCategory> categoryList = categoryService.findAll();
        System.out.println(categoryList);
        Assert.assertNotEquals(0,categoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(Arrays.asList(1,3,4));
        Assert.assertNotEquals(null,productCategoryList);

    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("你最好",5);
        ProductCategory category = categoryService.save(productCategory);
        Assert.assertNotEquals(null,category);
    }
}