package com.example.demo.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestAop implements ITestAop {
    public void test(){
        log.info("This is a test .. ") ;
    }
}
