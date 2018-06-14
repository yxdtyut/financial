package com.yxdtyut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author : yangxudong
 * @Description :   manager管理端的启动类
 * @Date : 上午10:21 2018/6/6
 */
@SpringBootApplication
@EnableScheduling
public class StartUp {
    public static void main(String[] args) {
        SpringApplication.run(StartUp.class);
    }
}
