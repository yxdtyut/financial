package com.yxdtyut.key;

/**
 * @Author : yangxudong
 * @Description :   产品key
 * @Date : 下午3:34 2018/5/29
 */
public class ProductKey extends BasePrefixKey {

    private ProductKey(Integer expireSeconds, String prefixKey) {
        super(expireSeconds, prefixKey);
    }

    private ProductKey(String prefixKey) {
        super(prefixKey);
    }

    public static ProductKey id = new ProductKey("id");
    public static ProductKey list = new ProductKey("list");
}
