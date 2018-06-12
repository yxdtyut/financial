package com.yxdtyut.saller.sign;

import com.yxdtyut.exception.GlobleException;
import com.yxdtyut.result.CodeMsg;
import com.yxdtyut.saller.service.SignService;
import com.yxdtyut.util.RSAUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : yangxudong
 * @Description :   签名aop
 * @Date : 下午3:09 2018/6/12
 */
@Aspect
@Component
public class SignAspect {

    @Autowired
    private SignService signService;

    @Before(value = "execution(* com.yxdtyut.saller.controller.*.*(..)) && args(authId,sign,signText,..)")
    public void verify(String authId, String sign, SignText signText) throws Throwable {
        //公钥一般保存在数据库当中
        String publicKey = signService.findPublicKeyByAuthId(authId);
        //公钥解密
        String publicText = RSAUtils.publicDecrypt(sign, RSAUtils.getPublicKey(publicKey));
        System.out.println(signText.toText());
        System.out.println(publicText);
        Assert.isTrue(replaceBlank(publicText).equals(replaceBlank(signText.toText())));
    }

    public String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
