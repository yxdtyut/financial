package com.yxdtyut.saller.service;

import com.yxdtyut.entity.Product;
import com.yxdtyut.enums.ProductStatusEnum;
import com.yxdtyut.key.ProductKey;
import com.yxdtyut.redis.RedisService;
import com.yxdtyut.rpc.ProductRpc;
import com.yxdtyut.rpc.domain.ProductRpcDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
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
public class ProductRpcService implements InitializingBean{

    @Autowired
    private ProductRpc productRpc;

    @Autowired
    private RedisService redisService;

    public List<Product> findAll() {
        log.info("saller客户端从缓存调用产品集合查询");
        List<Product> redisProductList = redisService.getList(ProductKey.list,"",Product.class);
        if (null != redisProductList && redisProductList.size() > 0) {
            log.info("saller客户端从缓存调用产品集合查询,结果:{}", redisProductList);
            return redisProductList;
        }
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
        log.info("缓存中查询单个产品,参数:{}",id);
        Product redisProduct = redisService.get(ProductKey.id, id, Product.class);
        if (null != redisProduct) {
            log.info("缓存中查询单个产品,结果:{}",redisProduct);
            return redisProduct;
        }
        log.info("saller客户端调用产品查询,参数:{}",id);
        Product product = productRpc.findOne(id);
        log.info("saller客户端调用产品查询,结果:{}",product);
        return product;
    }
    /**
     * @Author : yangxudong
     * @Description : 初始化产品进入redis缓存
     * @param null
     * @Date : 下午4:55 2018/6/11
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<Product> productList = findAll();
        productList.forEach(product -> {
            redisService.set(ProductKey.id, product.getId(), product);
        });
        redisService.set(ProductKey.list, "", productList);
    }

//    @PostConstruct
//    public void init() {
//        findOne("0002");
//    }
}
