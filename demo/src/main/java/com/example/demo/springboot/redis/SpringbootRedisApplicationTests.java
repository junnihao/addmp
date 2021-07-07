package com.example.demo.springboot.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/*
Spring Boot 集成Redis遇到的问题（一） ：Unable to connect to Redis;
刚刚学习Spring Boot相关知识，比较迷茫，在集成Redis数据库的时候 就出现了一些问题，遇到的第一个问题就是无法连接到Redis，以下为错误log:
org.springframework.data.redis.RedisConnectionFailureException: Unable to connect to Redis; nested exception is io.lettuce.core.RedisConnectionException: Unable to connect to localhost:6379
解决方法：
如果有同样遇到该问题的同学，首先别怀疑自己的代码问题，而是看看有没有启动Redis服务器（redis-server.exe）：
如果已经排除了该问题的存在，在考虑别的原因：
1.application.yml的redis配置中的spring.redis.timeout中连接超时时间（毫秒）中时间设置不能为0
2.找到redis的配置文件redis.conf ：vim redis.conf 修改 protected-mode yes 改为：protected-mode no
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {
    @Test
    public void contextLoads() {
    }

    @Autowired
    RedisDao redisDao;
    @Test
    public void testRedis(){
        redisDao.setValue("name","forezp");
        redisDao.setValue("age","11");
        log.info(redisDao.getValue("name"));
        log.info(redisDao.getValue("age"));
    }
}
