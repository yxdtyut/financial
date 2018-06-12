package com.yxdtyut.test;

import com.yxdtyut.util.RSAUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午1:36 2018/6/12
 */

public class RSATest {

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String data = "{\n" +
                "  \"amount\": 6000,\n" +
                "  \"chanId\": \"123\",\n" +
                "  \"chanUserId\": \"123\",\n" +
                "  \"createAt\": \"2018-06-12 07:54:59\",\n" +
                "  \"memo\": \"哈哈\",\n" +
                "  \"outerOrderId\": \"01011\",\n" +
                "  \"productId\": \"0002\",\n" +
                "  \"updateAt\": \"2018-06-12 07:54:59\"\n" +
                "}";
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALfcgdD6bZW35Ml-oty1RguMKGmT9XKY4dsrI6xdH0lyuEzu-IxfhtJgYRzN1BA2o7o1vOUHblUgYl8PpZZ7qeC_wrN9kD1sKzxG73kCWbFPVk_AJoejb3-03f0gkCpIDF7dMnMZVd5ACAdzzH-xf2yg2fgskjuum5XB__hNZ25pAgMBAAECgYBITtJHC5X5YZ76l3EZd7kaZX6nNuc6sGFyvl6DNQRO8G0G5JyfKBpvtjO7Dj-uOK7fVq75K5j6pYfKdu7aZbwC2hYv_CjVp6jUnY3jknyz8f1EC277BWpGtN1Y6YycuB8zZW-5e8OBtpPGTG-cfNaoFHCU0nvHx0VaiB-LKNR_AQJBAPCzKk1BWurGIh9Dm7-Ii1HWnEYIW5CCS9bk6L8EFdJ090bpZvlNen7738PN9qX5ZWT7Ecm1ihqFU6GuT5Xe5HkCQQDDjG0iyrR-RXRbpyo7682LYeInHJ5BABzkTzJPBJQGY1fjK-qJ3RHAKJzjhmH8ajA0VdbDAKqimNUxH77Xwf1xAkEAzrKc9gDUqV4COWTen8kLEvk_8-gDcYJizCSBNl-0uakWHEAc3KPLOLblPollcLxNdT0h1lzePs4VtncxAtuIyQJACjs9tpVOjlDJ_sWnUw1KauGLOKqpqRL9cACozDOcHx1xisRQeDgl5pz5YHo2hEN2FDbtjrRpOSqv9OsJTDatMQJAPrdiXfYRSjaBKVUrYpTiI3pkEk_JRpkZcPubM4XAzgLRIXDj0kkKauk19Zuf0Op4Yd6nh8pLmCzi4AYcKuEqOg\n";
        String s = RSAUtils.privateEncrypt(data, RSAUtils.getPrivateKey(privateKey));
        System.out.println(s);

//        Map<String, String> keyMap = RSAUtils.createKeys(1024);
//        String  publicKey = keyMap.get("publicKey");
//        String  privateKey = keyMap.get("privateKey");
//        System.out.println("公钥: \n\r" + publicKey);
//        System.out.println("私钥： \n\r" + privateKey);
//
//        System.out.println("公钥加密——私钥解密");
//        String str = "站在大明门前守卫的禁卫军，事先没有接到\n" +
//                "有关的命令，但看到大批盛装的官员来临，也就\n" +
//                "以为确系举行大典，因而未加询问。进大明门即\n" +
//                "为皇城。文武百官看到端门午门之前气氛平静，\n" +
//                "城楼上下也无朝会的迹象，既无几案，站队点名\n" +
//                "的御史和御前侍卫“大汉将军”也不见踪影，不免\n" +
//                "心中揣测，互相询问：所谓午朝是否讹传？";
//        System.out.println("\r明文：\r\n" + str);
//        System.out.println("\r明文大小：\r\n" + str.getBytes().length);
//        String encodedData = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(publicKey));
//        System.out.println("密文：\r\n" + encodedData);
//        String decodedData = RSAUtils.privateDecrypt(encodedData, RSAUtils.getPrivateKey(privateKey));
//        System.out.println("解密后文字: \r\n" + decodedData);
    }

    public void test() throws InvalidKeySpecException, NoSuchAlgorithmException {
        Map<String, String> keyMap = RSAUtils.createKeys(1024);
        String  publicKey = keyMap.get("publicKey");
        String  privateKey = keyMap.get("privateKey");
        System.out.println("公钥: \n\r" + publicKey);
        System.out.println("私钥： \n\r" + privateKey);

        System.out.println("公钥加密——私钥解密");
        String str = "站在大明门前守卫的禁卫军，事先没有接到\n" +
                "有关的命令，但看到大批盛装的官员来临，也就\n" +
                "以为确系举行大典，因而未加询问。进大明门即\n" +
                "为皇城。文武百官看到端门午门之前气氛平静，\n" +
                "城楼上下也无朝会的迹象，既无几案，站队点名\n" +
                "的御史和御前侍卫“大汉将军”也不见踪影，不免\n" +
                "心中揣测，互相询问：所谓午朝是否讹传？";
        System.out.println("\r明文：\r\n" + str);
        System.out.println("\r明文大小：\r\n" + str.getBytes().length);
        String encodedData = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(publicKey));
        System.out.println("密文：\r\n" + encodedData);
        String decodedData = RSAUtils.privateDecrypt(encodedData, RSAUtils.getPrivateKey(privateKey));
        System.out.println("解密后文字: \r\n" + decodedData);
    }

}
