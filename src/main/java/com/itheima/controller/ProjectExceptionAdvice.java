package com.itheima.controller;

import com.itheima.exception.BusinessException;
import com.itheima.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex){
        //1.记录日志
        //2.发送消息给运维
        //3.发送邮件给运维
        System.out.println(ex.getMessage());
        return new Result(ex.getCode(),null,ex.getMessage());
    }
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException e){
        System.out.println(e.getMessage());
        return new Result(e.getCode(),null,e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public void doException(Exception e){
        System.out.println("异常已被统一处理"+e.getMessage());
    }
}
