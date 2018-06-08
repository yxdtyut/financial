package com.yxdtyut.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author : yangxudong
 * @Description :   swagger属性配置
 * @Date : 下午2:26 2018/6/8
 */
@Data
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    private String groupName = "controller";
    private String basePackage;
    private String path;
    private String title = "HTTP API";
    private String description = "HTTP REQUEST";
    private String serviceUrl = "http://springfox.io";
    private String contact = "yxdjsw";
    private String license = "Apache License Version 2.0";
    private String licenseUrl = "https://github.com/springfox/springfox";
    private String versions;
}
