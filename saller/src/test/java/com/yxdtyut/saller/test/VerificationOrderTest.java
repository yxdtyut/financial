package com.yxdtyut.saller.test;

import com.yxdtyut.saller.service.VerificationOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午11:07 2018/6/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VerificationOrderTest {

    @Autowired
    private VerificationOrderService verificationOrderService;

    @Test
    public void testVerification() {
        Date date = new GregorianCalendar(2018, 5, 12).getTime();
        File file = verificationOrderService.makeVerificationFile("123", date);
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void getRemoteVerifyAndSave() {
        Date date = new GregorianCalendar(2018,5,12).getTime();
        verificationOrderService.saveRemoteVerificationOrder("123",date);
    }

    @Test
    public void verify() {
        Date date = new GregorianCalendar(2018,5,12).getTime();
        List<String> errors = verificationOrderService.verifyOrder("123", date);
        errors.forEach(error -> {
            System.out.println(error);
        });
    }
}
