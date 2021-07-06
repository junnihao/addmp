package com.example.demo.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestAop implements ITestAop {
    public void test(){
        log.info("This is a test .. ") ;
    }
}
