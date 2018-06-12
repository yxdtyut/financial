package com.yxdtyut.saller.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @Author : yangxudong
 * @Description :   签名明文
 * @Date : 下午3:12 2018/6/12
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)  //json的时候不包含null
@JsonPropertyOrder(alphabetic = true)   //字典排序
public interface SignText {
    default String toText() {
        return JSONObject.toJSONString(this, SerializerFeature.WriteDateUseDateFormat);
    }
}
