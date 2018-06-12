package com.yxdtyut.saller.service;

import com.yxdtyut.entity.Order;
import com.yxdtyut.entity.Product;
import com.yxdtyut.enums.OrderStatusEnum;
import com.yxdtyut.enums.OrderTypeEnum;
import com.yxdtyut.exception.GlobleException;
import com.yxdtyut.result.CodeMsg;
import com.yxdtyut.saller.repository.OrderRepository;
import com.yxdtyut.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:09 2018/6/12
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRpcService productRpcService;

    public Order saveOrder(Order order) {
        Product product = productRpcService.findOne(order.getProductId());
        if (null == product) {
            log.error("产品不存在");
            throw new GlobleException(CodeMsg.PRODUCT_NOT_EXIST);
        }
        if (order.getAmount().compareTo(product.getThresholdAmount()) < 0) {
            log.error("订单金额小于起投金额");
            throw new GlobleException(CodeMsg.AMOUNT_SMALL_THRESHOLDAMOUNT);
        } else if(null != product.getStepAmount() && product.getStepAmount().compareTo(new BigDecimal(0)) != 0){
            BigDecimal shang = order.getAmount().subtract(product.getStepAmount());
            BigDecimal[] bigDecimals = shang.divideAndRemainder(product.getStepAmount());
            BigDecimal yushu = bigDecimals[1];
            if (yushu.compareTo(new BigDecimal(0)) != 0) {
                log.error("超过起投金额的部分不是步长的整数倍");
                throw new GlobleException(CodeMsg.AMOUNT_NOTINTEGER_STEP);
            }
        }
        order.setOrderType(OrderTypeEnum.APPLY.name());
        order.setOrderStatus(OrderStatusEnum.SUCCESS.name());
        order.setOrderId(UUIDUtils.uuid());
        order.setCreateAt(new Date());
        log.info("创建订单,参数:{}",order);
        order = orderRepository.saveAndFlush(order);
        log.info("创建订单,结果:{}",order);
        return order;
    }
}
