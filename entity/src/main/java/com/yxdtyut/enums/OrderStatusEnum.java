package com.yxdtyut.enums;

import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   产品状态枚举
 * @Date : 上午9:38 2018/6/6
 */
@Getter
public enum OrderStatusEnum {
    INIT("INIT","初始化"),
    PROCESS("PROCESS","处理中"),
    SUCCESS("SUCCESS","成功"),
    FAIL("FAIL","失败");
    private String code;
    private String desc;

    OrderStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
