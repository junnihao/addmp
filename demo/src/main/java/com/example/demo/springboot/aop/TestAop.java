package com.example.demo.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TestAop {

    void contextLoads() {
    }

    @Test
    public void test(){
        log.info("This is a test .. ") ;
    }
}
