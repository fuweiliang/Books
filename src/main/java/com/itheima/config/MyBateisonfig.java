package com.itheima.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MyBateisonfig {
    @Bean  //sqlSessionFactoryBean整合包，可以加速开发    引用类型注入只需要为bean定义方法设置形参即可，容器会根据类型自动装配对象!!!!!!!!
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        //1.创建sqlSessionFactoryBean对象，因为有整合的开发
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //2.初始化类型别名调用setTypeAliasesPackage方法以指定获取包
        sqlSessionFactoryBean.setTypeAliasesPackage("com.itheima.domain");
        //3.获取初始化DataSource设置方法
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;//返回sqlSessionFactoryBean工厂对象
    }

    @Bean  //MapperScannerConfigurer映射文件扫描
    public MapperScannerConfigurer mapperScannerConfigurer() {
        //1.创建MapperScannerConfigurer对象
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //2.setBasePackage方法用于配置映射包具体位置
        mapperScannerConfigurer.setBasePackage("com.itheima.dao");
        return mapperScannerConfigurer;//返回MapperScannerConfigurer对象
    }
//        <mappers>                                               <!-- 初始化映射配置 -->
//        <package name="comitheima.dao"/>
//    </mappers>  映射文件是一个单独的Bean，所以需要单独配置
}
