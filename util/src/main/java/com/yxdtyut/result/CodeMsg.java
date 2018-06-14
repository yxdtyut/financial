package com.yxdtyut.result;

import lombok.Data;
import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   错误码
 * @Date : 下午3:04 2018/5/29
 */
@Getter
public class CodeMsg {
    /**通用模块.*/
    public static final CodeMsg SUCCESS = new CodeMsg(200, "成功");
    public static final CodeMsg SERVER_ERROR = new CodeMsg(5001, "服务异常");
    public static final CodeMsg RSA_SIGN_ERROR = new CodeMsg(5002, "验签失败");
    public static final CodeMsg PARAM_ERROR = new CodeMsg(400);

    /** 产品模块600xxx.*/
    public static final CodeMsg PRODUCT_NOT_EXIST = new CodeMsg(600001, "产品不存在");

    /** 订单模块700xxx.*/
    public static final CodeMsg AMOUNT_SMALL_THRESHOLDAMOUNT = new CodeMsg(700001, "订单金额小于起投金额");
    public static final CodeMsg AMOUNT_NOTINTEGER_STEP = new CodeMsg(700001, "超过起投金额的部分不是步长的整数倍");
    public static final CodeMsg CHAN_NOT_RIGHT = new CodeMsg(700002, "渠道编号有误");


    private Integer code;
    private String msg;

    private CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private CodeMsg() {
    }


}
