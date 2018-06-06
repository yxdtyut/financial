package com.yxdtyut.manager.service;

import com.yxdtyut.entity.Product;
import com.yxdtyut.enums.ProductStatusEnum;
import com.yxdtyut.manager.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :   产品service
 * @Date : 上午10:50 2018/6/6
 */
@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        log.debug("保存产品,参数:{}" , product);
        //设置默认值
        productDefaultMessage(product);
        //保存
        Product returnProduct = productRepository.save(product);
        log.debug("保存产品,结果:{}" , returnProduct);
        return returnProduct;
    }

    /**
     * @Author : yangxudong
     * @Description :  设置产品的默认值
     * @param product
     * @Date : 下午2:09 2018/6/6
     */
    private void productDefaultMessage(Product product) {
        if (product.getCreateAt() == null) {
            product.setCreateAt(new Date());
        }
        if (product.getUpdateAt() == null) {
            product.setUpdateAt(new Date());
        }
        if (product.getLock_term() == null) {
            product.setLock_term(0);
        }
        if (product.getStep_amount() == null) {
            product.setStep_amount(BigDecimal.ZERO);
        }
        if (product.getStatus() == null) {
            product.setStatus(ProductStatusEnum.AUDINTING.getDesc());
        }
    }
}
