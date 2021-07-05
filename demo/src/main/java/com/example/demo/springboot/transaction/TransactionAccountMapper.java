package com.example.demo.springboot.transaction;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TransactionAccountMapper {
    @Update("update demo_account set money = #{money} where id = #{id}")
    int update(@Param("money") double money, @Param("id") int id);
}
