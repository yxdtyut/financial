package com.yxdtyut.entity;

import com.yxdtyut.annotation.RewardRateScope;
import com.yxdtyut.annotation.StepAmountMustInt;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private String id;

    /** 产品名称.*/
    @NotNull
    private String name;

    /** 起投金额.*/
    @NotNull
    private BigDecimal threshold_amount;

    /** 投资步长.*/
    @StepAmountMustInt
    public BigDecimal step_amount;

    /** 锁定期.*/
    private Integer lock_term;

    /** 收益率，0-100 百分比值.*/
    @NotNull
    @RewardRateScope
    public BigDecimal reward_rate;

    /** 状态,audinting:审核中,in_sell:销售中,locked:暂停销售,finished:已结束.*/
    @NotNull
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
