package com.yxdtyut.config;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午11:00 2018/6/7
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> map =  super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.clear();
        map.put("author", "yxd"); 
        Map<String,Object> extMap = (Map<String, Object>) requestAttributes.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);
        map.put("errorMessage", extMap.get("errorMessage"));
        if (null != extMap.get("exception")) {
            map.put("exception", extMap.get("exception"));
        }
        return map;
    }
}
