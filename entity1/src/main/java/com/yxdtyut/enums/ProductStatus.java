package com.yxdtyut.enums;

import lombok.Getter;


/**
 * @Author : yangxudong
 * @Description :   产品状态的枚举类
 * @Date : 下午6:33 2018/6/5
 */
@Getter
public enum ProductStatus {
    AUDINTING("审核中"),
    IN_SELL("销售中"),
    LOCKED("暂停销售"),
    FINISHED("已结束"),
    ;
    private String desc;

    ProductStatus(String desc) {
        this.desc = desc;
    }
}
