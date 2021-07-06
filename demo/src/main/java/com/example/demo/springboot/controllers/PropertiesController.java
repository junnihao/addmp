package com.example.demo.springboot.controllers;

import com.example.demo.springboot.properties.NeoProperties;
import com.example.demo.springboot.properties.PropertiesFromAssignPath;
import com.example.demo.springboot.properties.PropertiesFromClassPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
1、@Controller 处理http请求
  @Controller 用来响应页面，@Controller必须配合模版来使用。spring-boot 支持多种模版引擎包括：
        1，FreeMarker
        2，Groovy
        3，Thymeleaf （Spring 官网使用这个）
        4，Velocity
        5，JSP （貌似Spring Boot官方不推荐，STS创建的项目会在src/main/resources 下有个templates 目录，这里就是让我们放模版文件的，
           然后并没有生成诸如 SpringMVC 中的webapp目录）

   @RestController
       Spring4之后新加入的注解，原来返回json需要@ResponseBody和@Controller配合。
       即@RestController是@ResponseBody和@Controller的组合注解。

   @RequestMapping 配置url映射
       @RequestMapping此注解即可以作用在控制器的某个方法上，也可以作用在此控制器类上。
       当控制器在类级别上添加@RequestMapping注解时，这个注解会应用到控制器的所有处理器方法上。
       处理器方法上的@RequestMapping注解会对类级别上的@RequestMapping的声明进行补充。
 */
@Slf4j
@RestController
@RequestMapping("/demo")
public class PropertiesController {
    @Autowired
    NeoProperties np ;
    @Autowired
    PropertiesFromClassPath pm ;

    @Autowired
    PropertiesFromAssignPath pm2 ;

    @Value("${com.neo.title}")
    String test ;

    @RequestMapping(value = "/properties")
    public String index() {
        log.info("  >>>>>>>>>>>>>  " +np.getDescription()); ;
        log.info("  >>>>>>>>>>>>> PropertiesFromClassPath  " +pm.getUpgradeFromXAtoXBEN()) ;
        log.info("  >>>>>>>>>>>>> PropertiesFromAssignPath  " +pm2.getUpgradeFromXAtoXBEN()) ;
        log.info("  >>>>>>>>>>>>>  " +test) ;
        return "Hello World";
    }
}
