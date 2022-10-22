package com.itheima.mapper;

import com.itheima.pojo.User;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from tb_user where username= #{username} and password= #{password}")
    User selectAll(@Param("username") String name,@Param("password") String password);

    //根据用户名查询用户
    @Select("select *from tb_user where  username=#{usename}")
    User selectByUserName( String usename);

    @Insert("INSERT INTO tb_user (username, password, gender, addr) VALUES (#{username},#{password},null,null)")
    boolean addUser(User user);
}
