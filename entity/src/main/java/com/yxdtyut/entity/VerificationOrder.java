package com.yxdtyut.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :   对账订单
 * @Date : 上午9:33 2018/6/6
 */
@Data
@Entity
public class VerificationOrder {
    /** 订单编号.*/
    @Id
    private String orderId;

    /** 渠道编号.*/
    @NotNull
    private String chanId;

    /** 产品编号.*/
    @NotNull
    private String productId;

    /** 渠道用户编号.*/
    @NotNull
    private String chanUserId;

    /** 订单类型,APPLY:申购,REDEEM:赎回.*/
    private String orderType;

    /** 外部订单编号.*/
    @NotNull
    private String outerOrderId;

    /** 金额.*/
    @NotNull
    public BigDecimal amount;

    /** 创建时间.*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createAt;
}
