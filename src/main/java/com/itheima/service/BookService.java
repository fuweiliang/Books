package com.itheima.service;

import com.itheima.domain.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional//平台事务管理器
public interface BookService {
    /*
    插入数据
     */

    boolean save(Book book);
    /*
    更新数据
     */
    boolean update(Book book);
    /*
    根据id删除
     */
    boolean delete(Integer id);
    /*
    根据id查询
     */
    Book getById(Integer id);
    /*
    查询所有
     */
    List<Book> getAll();
}
