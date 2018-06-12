package com.yxdtyut.saller.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yxdtyut.saller.sign.SignText;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :   订单参数
 * @Date : 下午3:15 2018/6/12
 */
@Data
public class OrderParam implements SignText{

    /** 渠道编号.*/
    @NotNull
    private String chanId;

    /** 产品编号.*/
    @NotNull
    private String productId;

    /** 渠道用户编号.*/
    @NotNull
    private String chanUserId;

    /** 外部订单编号.*/
    @NotNull
    private String outerOrderId;

    /** 金额.*/
    @NotNull
    public BigDecimal amount;

    /** 备注.*/
    private String memo;

    /** 创建时间.*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createAt;

    /** 更新时间.*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateAt;
}
