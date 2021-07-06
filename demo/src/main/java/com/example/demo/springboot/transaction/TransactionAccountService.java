package com.example.demo.springboot.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import javax.transaction.Transactional;

@Service
public class TransactionAccountService {
    @Autowired
    TransactionAccountMapper accountMapper;

    //@Transactional
    /*
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        当引入jdbc依赖之后，Spring Boot会自动默认分别注入DataSourceTransactionManager或JpaTransactionManager，
        所以我们不需要任何额外配置就可以用@Transactional注解进行事务的使用

        自己的事务管理器的时候可以重新返回一个PlatformTransactionManager对象，这些经常在使用动态数据源的时候需要设置自己的事务管理器
        @Bean
        public PlatformTransactionManager transactionManager() {
            // 配置事务管理, 使用事务时在方法头部添加@Transactional注解即可
            return new DataSourceTransactionManager(dynamicDataSource());
        }
    */
    public void transfer() throws RuntimeException{
        accountMapper.update(3010,1);//用户1减10块 用户2加10块
        int i=1/0;
        accountMapper.update(1100,2);
    }
}
