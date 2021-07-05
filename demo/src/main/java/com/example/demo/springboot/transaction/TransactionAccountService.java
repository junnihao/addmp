package com.example.demo.springboot.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TransactionAccountService {
    @Autowired
    TransactionAccountMapper accountMapper;

    @Transactional
    public void transfer() throws RuntimeException{
        accountMapper.update(900,1);//用户1减10块 用户2加10块
        //int i=1/0;
        //accountMapper.update(1100,2);
    }
}
