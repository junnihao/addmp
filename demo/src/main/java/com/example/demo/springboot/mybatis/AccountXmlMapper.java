package com.example.demo.springboot.mybatis;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountXmlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}
