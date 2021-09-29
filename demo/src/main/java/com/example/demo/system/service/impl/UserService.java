package com.example.demo.system.service.impl;

import com.example.demo.springboot.mybatis.AccountAnnotationMapper;
import com.example.demo.system.entity.User;
import com.example.demo.system.repository.mapper.UserMapper;
import com.example.demo.system.service.iface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public User findUser(String userName , String password){
        return userMapper.findUser(userName,password);
    }
}
