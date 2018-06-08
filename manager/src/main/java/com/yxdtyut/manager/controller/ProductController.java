package com.yxdtyut.manager.controller;

import com.yxdtyut.entity.Product;
import com.yxdtyut.manager.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午10:53 2018/6/6
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    @ApiOperation(value = "创建产品", notes = "根据对应业务规则添加产品")
    public Product save(@Valid @RequestBody Product product) {
        log.info("保存产品,参数:{}" , product);
        Product returnProduct = productService.saveProduct(product);
        log.info("保存产品,结果:{}:", returnProduct);
        return returnProduct;
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable String id) {
        log.info("查询产品,id={}", id);
        Product product = productService.findOne(id);
        log.info("查询产品,结果={}", product);
        return product;
    }

    @GetMapping
    public Page<Product> searchProduct(String ids
            , BigDecimal minRewardRate
            , BigDecimal maxRewardRate
            , String status
            , @PageableDefault Pageable pageable) {
        log.info("查询产品,ids={},最小收益率={},最大收益率={},状态S={},分页对象={}", ids, minRewardRate, maxRewardRate, status, pageable);
        List idList = null, statuList = null;
        if (ids != null) {
            idList = Arrays.asList(ids.split(","));
        }
        if (status != null) {
            statuList = Arrays.asList(status.split(","));
        }
        Page<Product> productPage = productService.search(idList, minRewardRate, maxRewardRate, statuList, pageable);
        log.info("查询产品,结果={}", productPage);
        return productPage;
    }

}
