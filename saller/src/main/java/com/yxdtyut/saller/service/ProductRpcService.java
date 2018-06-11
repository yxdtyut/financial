package com.yxdtyut.saller.service;

import com.yxdtyut.entity.Product;
import com.yxdtyut.enums.ProductStatusEnum;
import com.yxdtyut.rpc.ProductRpc;
import com.yxdtyut.rpc.domain.ProductRpcDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午5:51 2018/6/8
 */
@Service
@Slf4j
public class ProductRpcService {

    @Autowired
    private ProductRpc productRpc;

    public List<Product> findAll() {

        ProductRpcDomain productRpcDomain = new ProductRpcDomain();
        ArrayList statusList = new ArrayList();
        statusList.add(ProductStatusEnum.AUDINTING.name());
        productRpcDomain.setStatusList(statusList);
        log.info("saller客户端调用产品查询,参数:{}",productRpcDomain);
        List<Product> productList = productRpc.findAll(productRpcDomain);
        log.info("saller客户端调用产品查询,结果:{}",productList);
        return productList;
    }

    public Product findOne(String id) {
        log.info("saller客户端调用产品查询,参数:{}",id);
        Product product = productRpc.findOne(id);
        log.info("saller客户端调用产品查询,结果:{}",product);
        return product;
    }

    @PostConstruct
    public void init() {
        findOne("0002");
    }
}
