package com.example.demo.springboot.jdbctemplates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/*
   @Service注解作用
      @Service用于标注业务层组件
      @Controller用于标注控制层组件(如struts中的action)
      @Repository用于标注数据访问组件，即DAO组件.

   1、 其getBean的默认名称是类名（头字母小写），可以@Service(“xxxx”)这样来指定，
   2、其定义的bean默认是单例的，可以使用@Service(“beanName”) @Scope(“prototype”)来改变。
   3、可以通过@PostConstruct和@PreDestroy指定初始化方法和销毁方法（方法名任意）
 */
public class AccountService implements IAccountService{
    @Autowired
    IAccountDAO accountDAO;
    @Override
    public int add(Account account) {
        return accountDAO.add(account);
    }

    @Override
    public int update(Account account) {
        return accountDAO.update(account);
    }

    @Override
    public int delete(int id) {
        return accountDAO.delete(id);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

    @Override
    public List<Account> findAccountList() {
        return accountDAO.findAccountList();
    }
}
