package com.wjhcompany.weixineat.service.impl;

import com.wjhcompany.weixineat.dao.ProductInfoDao;
import com.wjhcompany.weixineat.dto.CartDTO;
import com.wjhcompany.weixineat.entity.ProductInfo;
import com.wjhcompany.weixineat.enums.ProductStatusEnum;
import com.wjhcompany.weixineat.enums.ResultEnum;
import com.wjhcompany.weixineat.exception.SellException;
import com.wjhcompany.weixineat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * @author JH Wei
 * @date 2019/3/13-20:59
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(0);
    }

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findById(productId).get();
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO: cartDTOList)
        {
            ProductInfo productInfo = productInfoDao.findByProductId(cartDTO.getProductId());
            if (productInfo == null)
            {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoDao.save(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
       for (CartDTO cartDTO: cartDTOList)
       {
           ProductInfo productInfo = productInfoDao.findByProductId(cartDTO.getProductId());
           if (productInfo == null ){
               throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
           }

           Integer result = productInfo.getProductStock()-cartDTO.getProductQuantity();
           if (result < 0){
               throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);

           }
           productInfo.setProductStock(result);

           productInfoDao.save(productInfo);
       }
    }
}
