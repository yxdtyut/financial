package com.yxdtyut.saller.controller;

import com.yxdtyut.entity.Product;
import com.yxdtyut.saller.service.ProductRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午5:05 2018/6/11
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRpcService productRpcService;

    @GetMapping("/{id}")
    public Product findOne(@PathVariable String id) {
        return productRpcService.findOne(id);
    }

    @GetMapping("/list")
    public List<Product> findAll() {
        return productRpcService.findAll();
    }
}
