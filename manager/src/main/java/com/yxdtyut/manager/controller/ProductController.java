package com.yxdtyut.manager.controller;

import com.yxdtyut.entity.Product;
import com.yxdtyut.enums.ProductStatusEnum;
import com.yxdtyut.manager.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

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
    public Product save(@Valid @RequestBody Product product) {
        log.info("保存产品,参数:{}" , product);
        Product returnProduct = productService.saveProduct(product);
        log.info("保存产品,结果:{}:", returnProduct);
        return returnProduct;
    }


}
