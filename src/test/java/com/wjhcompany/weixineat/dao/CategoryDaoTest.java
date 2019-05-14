package com.wjhcompany.weixineat.dao;

import com.wjhcompany.weixineat.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author JH Wei
 * @date 2019/3/13-15:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void findoneTest() {

        Optional<ProductCategory> productCategory = categoryDao.findById(1);
        System.out.println(productCategory.toString());
    }

    @Test
    @Transactional
    public void saveTest(){

        ProductCategory productCategory = new ProductCategory("女生最爱",5);
        categoryDao.save(productCategory);
        Assert.assertNotNull(productCategory);

    }

    @Test
    public void modTest(){
        ProductCategory category = categoryDao.findById(1).get();
        category.setCategoryName("中性最爱");
        categoryDao.save(category);

    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1,3,4);

        List<ProductCategory> productCategoryList = categoryDao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,productCategoryList.size());
    }
}