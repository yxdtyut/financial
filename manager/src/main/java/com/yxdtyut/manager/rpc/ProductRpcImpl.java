package com.yxdtyut.manager.rpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.yxdtyut.entity.Product;
import com.yxdtyut.manager.service.ProductService;
import com.yxdtyut.rpc.ProductRpc;
import com.yxdtyut.rpc.domain.ProductRpcDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午5:06 2018/6/8
 */
@Service
@AutoJsonRpcServiceImpl
@Slf4j
public class ProductRpcImpl implements ProductRpc {

    @Autowired
    private ProductService productService;

    @Override
    public List<Product> findAll(ProductRpcDomain productRpcDomain) {
        log.info("RPC查询产品列表,参数:{}", productRpcDomain);
        Pageable pageable = new PageRequest(0,100, Sort.Direction.DESC,"rewardRate");
        Page<Product> productPage = productService.search(productRpcDomain.getIdList(), productRpcDomain.getMinRewardRate()
                , productRpcDomain.getMaxRewardRate(), productRpcDomain.getStatusList(), pageable);
        log.info("RPC查询产品列表,结果:{}",productPage);
        return productPage.getContent();
    }

    @Override
    public Product findOne(String id) {
        log.info("RPC查询单个产品,参数:{}", id);
        Product productServiceOne = productService.findOne(id);
        log.info("RPC查询单个产品,结果:{}", productServiceOne);
        return productServiceOne ;
    }
}
