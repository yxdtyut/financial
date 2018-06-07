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
    public static final CodeMsg SERVER_ERROR = new CodeMsg(500, "服务异常");
    public static final CodeMsg PARAM_ERROR = new CodeMsg(400);


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
