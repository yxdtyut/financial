package com.yxdtyut.enums;

import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   产品类型枚举
 * @Date : 上午9:38 2018/6/6
 */
@Getter
public enum OrderTypeEnum {
    APPLY("申购"),
    REDEEM("赎回");
    private String desc;

    OrderTypeEnum(String desc) {
        this.desc = desc;
    }
}
