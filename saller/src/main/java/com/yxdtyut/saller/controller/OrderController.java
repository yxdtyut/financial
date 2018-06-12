package com.yxdtyut.saller.controller;

import com.yxdtyut.entity.Order;
import com.yxdtyut.saller.params.OrderParam;
import com.yxdtyut.saller.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:06 2018/6/12
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Order createOrder(@RequestHeader String authId
            , @RequestHeader String sign
            , @RequestBody @Valid OrderParam orderParam) {
        Order order = new Order();
        BeanUtils.copyProperties(orderParam,order);
        return orderService.saveOrder(order);
    }
}
