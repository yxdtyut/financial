package com.yxdtyut.config;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author : yangxudong
 * @Description :   jsonrpc的自动配置类
 * @Date : 上午10:47 2018/6/11
 */
@Configuration
@Slf4j
public class MyJsonRpcAutoConfiguration {

    /**
     * @Author : yangxudong
     * @Description : 服务端的自动配置
     * @param null
     * @Date : 上午10:47 2018/6/11
     */
    @Bean
    public AutoJsonRpcServiceImplExporter rpcServiceImplExporter() {
        return new AutoJsonRpcServiceImplExporter();
    }

    /**
     * @Author : yangxudong
     * @Description : 客户端的自动配置
     * @param url
     * @Date : 上午10:49 2018/6/11
     */
    @Bean
    @ConditionalOnProperty(value = {"rpc.server.url","rpc.client.basepackage"})
    public AutoJsonRpcClientProxyCreator rpcClientProxyCreator(@Value("${rpc.server.url}") String url,
                                                               @Value("${rpc.client.basepackage}") String basepackage) {
        AutoJsonRpcClientProxyCreator creator = new AutoJsonRpcClientProxyCreator();
        try {
            creator.setBaseUrl(new URL(url));
        } catch (MalformedURLException e) {
            log.error("创建RPC服务地址错误",e);
        }
        creator.setScanPackage(basepackage);
        return creator;
    }
}
