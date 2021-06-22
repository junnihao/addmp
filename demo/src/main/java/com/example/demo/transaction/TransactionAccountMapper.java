package com.example.demo.transaction;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TransactionAccountMapper {
    int update(@Param("money") double money, @Param("id") int id);
}
