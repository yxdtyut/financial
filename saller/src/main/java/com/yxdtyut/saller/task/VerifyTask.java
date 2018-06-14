package com.yxdtyut.saller.task;

import com.yxdtyut.saller.enums.ChanEnum;
import com.yxdtyut.saller.service.VerificationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author : yangxudong
 * @Description :   定时对账任务
 * @Date : 下午2:02 2018/6/14
 */

@Component
public class VerifyTask {

    @Autowired
    private VerificationOrderService verificationOrderService;

    @Scheduled(cron = "* * 1,3,5 * * *")
    public void makeVerificationOrderFile() {
        Date yesDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        for (ChanEnum chanEnum : ChanEnum.values()) {
            verificationOrderService.makeVerificationFile(chanEnum.getChanId(),yesDate);
        }
    }

    @Scheduled(cron = "* * 2,4,6 * * *")
    public void verify() {
        Date yesDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        for (ChanEnum chanEnum : ChanEnum.values()) {
            verificationOrderService.verifyOrder(chanEnum.getChanId(),yesDate);
        }
    }
}
