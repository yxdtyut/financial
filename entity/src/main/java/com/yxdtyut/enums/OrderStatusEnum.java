package com.yxdtyut.enums;

import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   产品状态枚举
 * @Date : 上午9:38 2018/6/6
 */
@Getter
public enum OrderStatusEnum {
    INIT("初始化"),
    PROCESS("处理中"),
    SUCCESS("成功"),
    FAIL("失败");
    private String desc;

    OrderStatusEnum(String desc) {
        this.desc = desc;
    }
}
