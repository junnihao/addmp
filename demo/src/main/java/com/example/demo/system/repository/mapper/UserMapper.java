package com.example.demo.system.repository.mapper;


import com.example.demo.system.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select id as id,name as name, passwd as passwd from demo_user where name = #{userName} and passwd = #{password} ")
    User findUser(@Param("userName") String userName,@Param("password") String password);
}
