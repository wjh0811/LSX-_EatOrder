package com.wjhcompany.weixineat.dao;

import com.wjhcompany.weixineat.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JH Wei
 * @date 2019/3/13-15:53
 */
@Repository
public interface CategoryDao extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
