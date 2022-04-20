package com.example.demo.springboot.controllers;

import com.example.demo.springboot.jpa.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operator")
public class operatorController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testAutho() {
        return " it was operate by Operators ..";
    }
}
