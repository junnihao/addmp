package com.example.demo.springboot.jdbctemplates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/*
    @Repository：是spring的注解，主动标识当前类要交给spring容器管理（相当于@Component注解）。
                 如果不加这个注释，AccountService ：
                   @Autowired
                   IAccountDAO accountDAO;
                   则提示找不到bean，
                 另外标注：@Repository有特定的异常封装
 */

/* 其他
1,@MapperScan是要spring启动时，扫描到所有的Mapper文件，并生成代理类交给spring容器管理；
@Mapper注解，当项目启动时，扫描到被此注解标识接口类，就会创建代理类并交给spring容器管理。
2,如果不加@MapperScan注解，那么Mapper接口类上就要添加@Mapper注解。
3, @Repository注解是spring的注解，主动标识当前类要交给spring容器管理（相当于@Component注解）。
一般添加了@Mapper注解或者@MapperScan注解，那么就不用添加@Repository。因为@Mapper注解（@MapperScan注解）已将改接口的代理类给了spring容器管理。
但是，有时候IDEA会识别不了@Mapper注解，在项目自动编译的时候，项目会爆红色波浪线，加@Respository注解，可以抑制这种报错。
 */
public class AccountDaoImpl implements IAccountDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Account account) {
        return jdbcTemplate.update("insert into demo_account(name, money) values(?, ?)",
                account.getName(),account.getMoney());

    }

    @Override
    public int update(Account account) {
        return jdbcTemplate.update("UPDATE  demo_account SET NAME=? ,money=? WHERE id=?",
                account.getName(),account.getMoney(),account.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE from TABLE demo_account where id=?",id);
    }

    @Override
    public Account findAccountById(int id) {
        List<Account> list = jdbcTemplate.query("select * from demo_account where id = ?", new Object[]{id}, new BeanPropertyRowMapper(Account.class));
        if(list!=null && list.size()>0){
            Account account = list.get(0);
            return account;
        }else{
            return null;
        }
    }

    @Override
    public List<Account> findAccountList() {
        List<Account> list = jdbcTemplate.query("select * from demo_account", new Object[]{}, new BeanPropertyRowMapper(Account.class));
        if(list!=null && list.size()>0){
            return list;
        }else{
            return null;
        }
    }
}
