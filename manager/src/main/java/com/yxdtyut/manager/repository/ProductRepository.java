package com.yxdtyut.manager.repository;

import com.yxdtyut.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author : yangxudong
 * @Description :   产品Repository
 * @Date : 上午10:47 2018/6/6
 */

public interface ProductRepository extends JpaRepository<Product,String>, JpaSpecificationExecutor<Product> {
}
