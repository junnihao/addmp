package com.example.demo.springboot.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MybatisAccountXmlService {

    @Autowired
    private AccountXmlMapper accountMapper;

    public Account selectByPrimaryKey(Integer id){
        return accountMapper.selectByPrimaryKey(id) ;
    }
}
