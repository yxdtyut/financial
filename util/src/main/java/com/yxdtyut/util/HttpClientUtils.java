package com.yxdtyut.util;

import com.squareup.okhttp.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author : yangxudong
 * @Description :   http调用工具类
 * @Date : 上午10:32 2018/4/17
 */
@Data
@Slf4j
public class HttpClientUtils {

    /**
     * post方式提交json代码
     * @throws Exception
     */
    public static String postJson(Object obj, String url){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, obj.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "51785769-0873-599b-9ae6-a647a3baca9c")
                .build();

        Response response = null;
        String string = null;
        try {
            response = client.newCall(request).execute();
            string = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * post方式提交json代码
     * @throws Exception
     */
    public static String postJsonWithToken(Object obj, String url, String token){
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, obj.toString());
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("token", token)
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "3e1ce8b3-92bf-b2df-0d0a-d1929a1a7eea")
                .build();

        Response response = null;
        String string = null;
        try {
            response = client.newCall(request).execute();
            string = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 发送 get请求
     * @throws Exception
     */
    public static String getJson(String url){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", "9e078511-89fd-7e44-6d4a-3b79bc460e0c")
                .build();

        Response response = null;
        String string = null;
        try {
            response = client.newCall(request).execute();
            string = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

}
