package com.example.demo.springboot.mybatis;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountAnnotationMapper {

    @Insert("insert into demo_account(name, money) values(#{name}, #{money})")
    int add(@Param("name") String name, @Param("money") double money);

    @Update("update demo_account set name = #{name}, money = #{money} where id = #{id}")
    int update(@Param("name") String name, @Param("money") double money, @Param("id") int  id);

    @Delete("delete from demo_account where id = #{id}")
    int delete(int id);

    @Select("select id, name as name, money as money from demo_account where id = #{id}")
    Account findAccount(@Param("id") int id);

    @Select("select id, name as name, money as money from demo_account")
    List<Account> findAccountList();
}
