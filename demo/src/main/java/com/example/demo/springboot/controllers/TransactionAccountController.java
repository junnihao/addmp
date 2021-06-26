package com.example.demo.springboot.controllers;

import com.example.demo.springboot.transaction.TransactionAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by  on
 */
@RestController
@RequestMapping("/transactionAccount")
public class TransactionAccountController {
    @Autowired
    TransactionAccountService accountService ;

    @RequestMapping(value = "/updateAccount", method = RequestMethod.GET)
    public void updateAccounts() {
        System.out.println("update account") ;
        accountService.transfer();
    }
}
