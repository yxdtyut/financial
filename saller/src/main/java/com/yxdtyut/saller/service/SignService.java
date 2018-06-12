package com.yxdtyut.saller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :   签名service
 * @Date : 下午3:07 2018/6/12
 */
@Service
public class SignService {

    static Map<String, String> PUBLIC_KEYS = new HashMap<>();
    static {
        PUBLIC_KEYS.put("1000","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC33IHQ-m2Vt-TJfqLctUYLjChpk_VymOHbKyOsXR9JcrhM7viMX4bSYGEczdQQNqO6NbzlB25VIGJfD6WWe6ngv8KzfZA9bCs8Ru95AlmxT1ZPwCaHo29_tN39IJAqSAxe3TJzGVXeQAgHc8x_sX9soNn4LJI7rpuVwf_4TWduaQIDAQAB\n");
    }

    /**
     * @Author : yangxudong
     * @Description :  根据授权编号获取公钥
     * @param authId
     * @Date : 下午3:24 2018/6/12
     */
    public String findPublicKeyByAuthId(String authId) {
        return PUBLIC_KEYS.get(authId);
    }
}
