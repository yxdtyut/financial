package com.yxdtyut.saller.repository;

import com.yxdtyut.entity.Order;
import com.yxdtyut.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:07 2018/6/12
 */

public interface OrderRepository extends JpaRepository<Order,String>, JpaSpecificationExecutor<Order> {
}
