package com.yxdtyut.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :   产品pojo
 * @Date : 下午6:13 2018/6/5
 */
@Data
@Entity
public class Product {
    /**
     * 产品id .
     */
    @Id
    private String id;

    /** 产品名称.*/
    private String name;

    /** 起投金额.*/
    private BigDecimal threshold_amount;

    /** 投资步长.*/
    public BigDecimal step_amount;

    /** 锁定期.*/
    private Integer lock_term;

    /** 收益率，0-100 百分比值.*/
    public BigDecimal reward_rate;

    /** 状态,audinting:审核中,in_sell:销售中,locked:暂停销售,finished:已结束.*/
    private String status;

    /** 备注.*/
    private String memo;

    /** 创建时间.*/
    private Date createAt;

    /** 创建者.*/
    private String createUser;

    /** 修改时间.*/
    private Date updateAt;

    /** 修改者.*/
    private String updateUser;
}
