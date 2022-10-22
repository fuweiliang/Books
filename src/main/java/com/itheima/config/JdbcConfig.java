package com.itheima.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * [第三方bean管理]
 * 使用@Bean配置第三方bean
 * spring没有bean识别
 * 怎么办呢？？？
 * 方式一：@Configuration在外部类定义注解，并使用扫描注解精确定位此类
 */
@Configuration
public class JdbcConfig {
    //1.定义一个方法获得要管理的对象   需要手动配置!

    //3.【简单类型的注入】 使用@Vlue值定义注解  了解即可
    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String Username;
    @Value("${jdbc.password}")
    private String Password;
    @Bean
    //2.添加@Bean，表示当前返回值是一个Bean
    public DruidDataSource dataSource() {
        //引用类型注入只需要为bean定义方法设置形参即可，容器会根据类型自动装配对象!!!!!!!!
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName(driver);
        dds.setUrl(url);
        dds.setUsername(Username);
        dds.setPassword(Password);
        //以上set是设置属性用于引用类型
        return dds;
    }

    /**
     * 设置事务管理器并交给pring管理
     * @Bean public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
     * //要求你提供对应的数据源
     * DataSourceTransactionManager dataSourceTransactionManager=new DataSourceTransactionManager();
     * dataSourceTransactionManager.setDataSource(dataSource);
     * return dataSourceTransactionManager;
     * }
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        //要求你提供对应的数据源
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);//不能用方法调用，否则此为java方法调用，不是spring
        return dataSourceTransactionManager;
    }

}
