package com.yxdtyut.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :   产品pojo
 * @Date : 上午9:33 2018/6/6
 */
@Data
@Entity
@Table(name = "order_t")
public class Order {
    /** 订单编号.*/
    @Id
    private String orderId;

    /** 渠道编号.*/
    private String chanId;

    /** 产品编号.*/
    private String productId;

    /** 渠道用户编号.*/
    private String chanUserId;

    /** 订单类型,APPLY:申购,REDEEM:赎回.*/
    private String orderType;

    /** 订单状态,INIT:初始化,PROCESS:处理中,SUCCESS:成功,FAIL:失败	.*/
    private String orderStatus;

    /** 外部订单编号.*/
    private String outerOrderId;

    /** 金额.*/
    public BigDecimal amount;

    /** 备注.*/
    private String memo;

    /** 创建时间.*/
    private Date createAt;

    /** 更新时间.*/
    private Date updateAt;
}
