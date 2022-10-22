package com.itheima.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;

//以下为简化开发！！！！！！！！！！！！！！
public class ServletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//4.定义一个servlet容器启动的配置类，在配置类里面加载spring配置
//public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {
//    //加载springmvc容器配置
//    @Override
//    protected WebApplicationContext createServletApplicationContext() {
//        /**
//         * AbstractDispatcherServletInitializer类是SpringWVc提供的快速初始化web3.0容器的抽象类AbstractDispatcherServletInitializer提供三个接口方法供用户实现
//         * createServletApplicationContext()方法，创建servlet容器时，加载SpringWVc对应的bean并放入
//         * MebApplicationContext对象范围中，而MebApplicationContext的作用范围为ServletContext范围，即整个web容器范围
//         */
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        //调用register注册bean
//        ctx.register(SpringMvcConfig.class);
//        return ctx;//将对象返回否则子容器会启动失败
//    }
//
//    //设置那些请求归属springmvc处理   属于web配置
//    @Override
//    protected String[] getServletMappings() {
//        //String[]{"/"}代表拦截所有请求
//        return new String[]{"/"};
//    }
//
//    //加载spring容器配置  属于根配置
//    @Override
//    protected WebApplicationContext createRootApplicationContext() {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        //调用register注册bean
//        ctx.register(SpringConfig.class);
//        return ctx;//将对象返回否则子容器会启动失败
//    }

//    @Override//乱码处理
//    protected Filter[] getServletFilters() {
//        CharacterEncodingFilter filter = new CharacterEncodingFilter();
//        filter.setEncoding("UTF-8");
//        return new Filter[]{filter};
//    }
}
