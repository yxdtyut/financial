package com.yxdtyut.saller.configuration;

import com.yxdtyut.entity.Order;
import com.yxdtyut.entity.VerificationOrder;
import com.yxdtyut.saller.repository.OrderRepository;
import com.yxdtyut.saller.repositoryBackup.VerificationOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.RepositoryBeanNamePrefix;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author : yangxudong
 * @Description :   JPA双数据源的配置
 * @Date : 下午3:15 2018/6/14
 */
@Configuration
public class DataAccessConfig {

    @Autowired
    private JpaProperties jpaProperties;

    /** 双数据源.*/
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.backup")
    public DataSource backupDataSource() {
        return DataSourceBuilder.create().build();
    }

    /** 双对象管理工厂 .*/
    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean orderEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("primaryDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(Order.class)
                .properties(getVendorProperties(dataSource))
                .persistenceUnit("primary")
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean verifyEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("backupDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages(VerificationOrder.class)
                .properties(getVendorProperties(dataSource))
                .persistenceUnit("backup")
                .build();
    }

    protected Map<String, Object> getVendorProperties(DataSource dataSource) {
        Map<String, Object> vendorProperties = new LinkedHashMap<String, Object>();
        vendorProperties.putAll(jpaProperties.getHibernateProperties(dataSource));
        return vendorProperties;
    }

    /** 双事务管理器.*/
    @Bean
    @Primary
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("orderEntityManagerFactory") LocalContainerEntityManagerFactoryBean managerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(managerFactoryBean.getObject());
        return transactionManager;
    }

    @Bean
    public PlatformTransactionManager backupTransactionManager(@Qualifier("verifyEntityManagerFactory") LocalContainerEntityManagerFactoryBean managerFactoryBean) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(managerFactoryBean.getObject());
        return transactionManager;
    }

    /** 采用不同的数据源定义不同或者相同的repository.*/
    @Configuration
    @Primary
    @EnableJpaRepositories(basePackageClasses = OrderRepository.class,
            entityManagerFactoryRef = "orderEntityManagerFactory"
            ,transactionManagerRef = "primaryTransactionManager")
    public class OrderConfiguration {

    }

    @Configuration
    @RepositoryBeanNamePrefix(value = "read")
    @EnableJpaRepositories(basePackageClasses = OrderRepository.class,
            entityManagerFactoryRef = "verifyEntityManagerFactory"
            ,transactionManagerRef = "backupTransactionManager")
    public class OrderReadConfiguration {

    }

    @Configuration
    @EnableJpaRepositories(basePackageClasses = VerificationOrderRepository.class,
            entityManagerFactoryRef = "verifyEntityManagerFactory"
            ,transactionManagerRef = "backupTransactionManager")
    public class VerifyConfiguration {

    }



}
