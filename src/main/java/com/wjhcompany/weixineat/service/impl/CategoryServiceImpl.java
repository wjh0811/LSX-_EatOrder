package com.wjhcompany.weixineat.service.impl;

import com.wjhcompany.weixineat.dao.CategoryDao;
import com.wjhcompany.weixineat.entity.ProductCategory;
import com.wjhcompany.weixineat.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
    * @Description: ${类目}
    * @Author by: JH WEI
    * @Date:
    * @Updated in:
    * @Modified By:
*/
@Service
public class CategoryServiceImpl  implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryDao.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return categoryDao.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return categoryDao.save(productCategory);
    }
}
