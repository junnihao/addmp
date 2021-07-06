package com.example.demo.springboot.controllers;

import com.example.demo.springboot.aop.ITestAop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/aop")
public class AopController {
    @Autowired
    ITestAop testAop ;

    @RequestMapping(value = "/test")
    public String index() {
        testAop.test();
        log.info("  >>>>>>>>>>>>>  " ); ;
        return "Hello";
    }
}
