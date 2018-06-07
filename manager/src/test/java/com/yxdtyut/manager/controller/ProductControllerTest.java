package com.yxdtyut.manager.controller;

import com.alibaba.fastjson.JSON;
import com.yxdtyut.entity.Product;
import com.yxdtyut.enums.ProductStatusEnum;
import com.yxdtyut.util.HttpClientUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:12 2018/6/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)   //修改测试用例顺序
public class ProductControllerTest {
    
    private RestTemplate restTemplate = new RestTemplate();
    
    
    @Value("http://localhost:${local.server.port}/manager/product")
    private String baseUrl;

    private static List<Product> rightProductList = new ArrayList<>();
    private static List<Product> errorProductList = new ArrayList<>();

    @BeforeClass
    public static void init() {
        Product p1 = new Product("0001","余额宝1号",new BigDecimal(5000),new BigDecimal(25.2));
        Product p2 = new Product("0002","余额宝2号",new BigDecimal(6000),new BigDecimal(28.6));
        Product p3 = new Product("0003","余额宝3号",new BigDecimal(7000),new BigDecimal(21.4));
        Product p4 = new Product("0004","余额宝4号",new BigDecimal(8000),new BigDecimal(22.8));
        rightProductList.add(p1);
        rightProductList.add(p2);
        rightProductList.add(p3);
        rightProductList.add(p4);
        /** id为null.*/
        Product e1 = new Product(null,"理财通1号",new BigDecimal(5000),new BigDecimal(25.2));
        /** 名称为null.*/
        Product e2 = new Product("0010",null,new BigDecimal(6000),new BigDecimal(28.6));
        /** 起投金额为null.*/
        Product e3 = new Product("0011","理财通2号",null,new BigDecimal(28.6));
        /** 收益率为null.*/
        Product e4 = new Product("0012","理财通3号",new BigDecimal(6300),null);
        /** 投资步长不为整数.*/
        Product e5 = new Product("0013","理财通4号",new BigDecimal(200),new BigDecimal(28.6));
        e5.setStepAmount(new BigDecimal(10.1));
        /** 收益率范围有误，大于30.*/
        Product e6 = new Product("0014","理财通5号",new BigDecimal(54000),new BigDecimal(31.4));
        /** 收益率范围有误，小于0.*/
        Product e7 = new Product("0015","理财通6号",new BigDecimal(1000),new BigDecimal(-0.1));
        errorProductList.add(e1);
        errorProductList.add(e2);
        errorProductList.add(e3);
        errorProductList.add(e4);
        errorProductList.add(e5);
        errorProductList.add(e6);
        errorProductList.add(e7);
    }

    @Test
    public void create() {
        rightProductList.forEach(product -> {
            String result = HttpClientUtils.postJson(JSON.toJSONString(product), baseUrl+"/save");
            Assert.assertEquals(JSON.toJavaObject(JSON.parseObject(result),Product.class).getName(), product.getName());
        });
    }

    @Test
    public void createException() {
        System.out.println("===" + baseUrl);
        errorProductList.forEach(product -> {
            String result = HttpClientUtils.postJson(JSON.toJSONString(product), baseUrl+"/save");
            Assert.assertTrue(result.contains("errorMessage"));
        });
    }
    
    @Test
    public void findOne() {
        rightProductList.forEach(product -> {
            Product resultProduct = restTemplate.getForObject(baseUrl + "/" + product.getId(),Product.class);
            Assert.assertTrue(resultProduct.getId().equals(product.getId()));
        });

        errorProductList.forEach(product -> {
            Product resultProduct = restTemplate.getForObject(baseUrl + "/" + product.getId(),Product.class);
            Assert.assertNull(resultProduct);
        });
    }

    @Test
    public void search() {
        rightProductList.forEach(product -> {
            String result1 = HttpClientUtils.getJson(baseUrl + "?ids=" + product.getId());
            String result2 = HttpClientUtils.getJson(baseUrl + "?minRewardRate=" + product.getRewardRate().divide(new BigDecimal(2)));
            String result3 = HttpClientUtils.getJson(baseUrl + "?maxRewardRate=" + product.getRewardRate().multiply(new BigDecimal(2)));
            String result4 = HttpClientUtils.getJson(baseUrl + "?status=" + ProductStatusEnum.AUDINTING);

            String result5 = HttpClientUtils.getJson(baseUrl + "?page=0");
            String result6 = HttpClientUtils.getJson(baseUrl + "?size=2");
            //System.out.println("---" + result4);
            Assert.assertTrue(result1.contains("rewardRate"));
            Assert.assertTrue(result2.contains("rewardRate"));
            Assert.assertTrue(result3.contains("rewardRate"));
            Assert.assertTrue(result4.contains("rewardRate"));
            Assert.assertTrue(result5.contains("rewardRate"));
            Assert.assertTrue(result6.contains("rewardRate"));
        });
    }

}
