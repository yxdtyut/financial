package org.springframework.data.repository.config;

import java.lang.annotation.*;

/**
 * @Author : yangxudong
 * @Description :   JPA读写分离(同一个Repository，根据不同的name连接不同的数据库
 *              ，这个是不同name的前缀)
 * @Date : 下午5:34 2018/6/14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RepositoryBeanNamePrefix {
    String value();
}
