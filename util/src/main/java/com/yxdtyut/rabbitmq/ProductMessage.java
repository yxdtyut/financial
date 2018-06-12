package com.yxdtyut.rabbitmq;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :   产品消息对象
 * @Date : 上午9:19 2018/6/12
 */
@Data
public class ProductMessage {
    private String id;
    private String status;

    public ProductMessage() {
    }

    public ProductMessage(String id, String status) {
        this.id = id;
        this.status = status;
    }
}
