package com.yxdtyut.manager.service;

import com.yxdtyut.entity.Product;
import com.yxdtyut.enums.ProductStatusEnum;
import com.yxdtyut.manager.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Product findOne(String id) {
        log.debug("查询产品,id={}", id);
        Assert.notNull(id,"查询产品的id不能为空");
        Product product = productRepository.findOne(id);
        log.debug("查询产品,结果={}", product);
        return product;
    }

    public Page<Product> search(List idList
                                , BigDecimal minRewardRate
                                , BigDecimal maxRewardRate
                                , List statusList
                                , Pageable pageable) {
        log.debug("查询产品，id集合={},最小收益率={},最大收益率={},状态集合={},分页对象={}",idList,minRewardRate,maxRewardRate,statusList,pageable);
        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Expression<String> idCol = root.get("id");
                Expression<BigDecimal> rewardRateCol = root.get("rewardRate");
                Expression<String> statusCol = root.get("status");
                List<Predicate> predicates = new ArrayList<>();
                if (idList != null && idList.size() > 0) {
                    predicates.add(idCol.in(idList));
                }
                if (minRewardRate != null && BigDecimal.ZERO.compareTo(minRewardRate) < 0) {
                    predicates.add(cb.ge(rewardRateCol, minRewardRate));
                }
                if (maxRewardRate != null && BigDecimal.ZERO.compareTo(maxRewardRate) < 0) {
                    predicates.add(cb.le(rewardRateCol, maxRewardRate));
                }
                if (statusList != null && statusList.size() > 0) {
                    predicates.add(statusCol.in(statusList));
                }
                query.where(predicates.toArray(new Predicate[0]));
                return null;
            }
        };
        Page<Product> productPage = productRepository.findAll(specification, pageable);
        log.debug("查询产品,结果={}", productPage);
        return productPage;
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
        if (product.getLockTerm() == null) {
            product.setLockTerm(0);
        }
        if (product.getStepAmount() == null) {
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getStatus() == null) {
            product.setStatus(ProductStatusEnum.AUDINTING.name());
        }
    }
}
