package com.yxdtyut.rpc;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.yxdtyut.entity.Product;
import com.yxdtyut.rpc.domain.ProductRpcDomain;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   产品相关的rpc服务
 * @Date : 下午4:56 2018/6/8
 */
@JsonRpcService("rpc/product")
public interface ProductRpc {
    /** 根据条件查询产品列表.*/
    List<Product> findAll(ProductRpcDomain productRpcDomain);

    /** 查询单个产品.*/
    Product findOne(String id);
}
