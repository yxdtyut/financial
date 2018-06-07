package com.yxdtyut.enums;

import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   产品类型枚举
 * @Date : 上午9:38 2018/6/6
 */
@Getter
public enum OrderTypeEnum {
    APPLY("APPLY","申购"),
    REDEEM("REDEEM","赎回");
    private String code;
    private String desc;

    OrderTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
