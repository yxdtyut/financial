package com.yxdtyut.enums;

import lombok.Getter;


/**
 * @Author : yangxudong
 * @Description :   产品状态的枚举类
 * @Date : 下午6:33 2018/6/5
 */
@Getter
public enum ProductStatusEnum {
    AUDINTING("AUDINTING","审核中"),
    IN_SELL("IN_SELL","销售中"),
    LOCKED("LOCKED","暂停销售"),
    FINISHED("FINISHED","已结束"),
    ;
    private String code;
    private String desc;

    ProductStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
