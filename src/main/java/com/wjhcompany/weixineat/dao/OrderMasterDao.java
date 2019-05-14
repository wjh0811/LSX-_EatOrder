package com.wjhcompany.weixineat.dao;

import com.wjhcompany.weixineat.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author JH Wei
 * @date 2019/3/15-18:27
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {

  Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
  OrderMaster findByOrderId(String orderId);
}
