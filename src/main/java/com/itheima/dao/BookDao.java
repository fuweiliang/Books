package com.itheima.dao;

import com.itheima.domain.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookDao {
    @Insert("INSERT INTO itheima.tbl_book(id, type, name, description)  VALUES (null, #{type},#{name},#{description})")
    int save(Book book);

    @Update("update itheima.tbl_book set type = #{type},name=#{name},description = #{description} where id=#{id} ")
    int update(Book book);

    @Delete("delete from itheima.tbl_book where id=#{id}")
    int delete(Integer id);

    @Select("SELECT *FROM itheima.tbl_book WHERE id=#{id}")
    Book getById(Integer id);

    @Select("SELECT t.* FROM itheima.tbl_book t ")
    List<Book> getAll();
}
