package com.wjhcompany.weixineat.dao;

import com.wjhcompany.weixineat.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author JH Wei
 * @date 2019/3/15-18:31
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String OrderId);

}
