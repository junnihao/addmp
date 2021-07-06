package com.example.demo.springboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
/*
https://www.jianshu.com/p/21f3e074e91a
@Configuration 用于定义配置类，可替换XML配置文件，被注解的类内部包含一个或多个@Bean
一、@Configuration 加载Spring方法
Car.java
public class Car {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
定义Config类
@Configuration
public class Config {
    public Config() {
        System.out.println("TestConfig容器初始化...");
    }

    @Bean(name = "getMyCar")
    public Car getCar() {
        Car c = new Car();
        c.setName("dankun");
        return c;
    }
}

实例化
public void testConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Car car = (Car)context.getBean("car");
        System.out.println(car.getName());
    }
// 输出
// TestConfig容器初始化...
// dankun


二、@Configuration + @Component：@Configuration也附带了@Component的功能。所以理论上也可以使用@Autowared功能。上述代码可以改成下面形式
Car.java
@Component
public class Car {
    @Value("dankun")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

Config.java
@Configuration
@ComponentScan("com.wuyue.annotation")
public class Config {
    public Config() {
        System.out.println("TestConfig容器初始化...");
    }

测试主入口
public class TestConfig {
    @Test
    public void testConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Car car = (Car)context.getBean("car");
        System.out.println(car.getName());
    }
}
// 输出
// TestConfig容器初始化...
// dankun


总结
@Configuation等价于<Beans></Beans>
@Bean 等价于<Bean></Bean>
@ComponentScan等价于<context:component-scan base-package="com.dxz.demo"/>
@Component 等价于<Bean></Bean>

@Bean VS @Component
两个注解的结果是相同的，bean都会被添加到Spring上下文中。
@Component 标注的是类,允许通过自动扫描发现。@Bean需要在配置类@Configuation中使用。
@Component类使用的方法或字段时不会使用CGLIB增强。而在@Configuration类中使用方法或字段时则使用CGLIB创造协作对象
假设我们需要将一些第三方的库组件装配到应用中或者 我们有一个在多个应用程序中共享的模块，它包含一些服务。并非所有应用都需要它们。

如果在这些服务类上使用@Component并在应用程序中使用组件扫描，我们最终可能会检测到超过必要的bean。导致应用程序无法启动
但是我们可以使用 @Bean来加载

因此，基本上，使用@Bean将第三方类添加到上下文中。和@Component，如果它只在你的单个应用程序中
*/
@Component
@PropertySource(value="classpath:pushMessageByGrade.properties",encoding="utf-8")
@ConfigurationProperties(prefix = "abc.edc")
/*
PropertySource：加载指定的属性文件
@PropertySource(value= {"classpath:config/xxx.properties"},ignoreResourceNotFound=false,encoding="UTF-8",name="jdbc-bainuo-dev.properties")
@ConfigurationProperties(prefix = "spring.datasource.shareniu",ignoreUnknownFields=true,ignoreInvalidFields=true)
*/
public class PropertiesFromClassPath {
    private String language ;
    private String os ;
    private String version;
    private String messageZH ;
    private String messageEN ;
    private String titleZH ;
    private String titleEN ;
    private String upgradeFromXAtoXBZH ;
    private String upgradeFromXAtoXBEN ;
    private String upgradeFromXAtoXHZH ;
    private String upgradeFromXAtoXHEN ;
    private String upgradeFromXBtoXHZH ;
    private String upgradeFromXBtoXHEN ;


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessageEN() {
        return messageEN;
    }

    public void setMessageEN(String messageEN) {
        this.messageEN = messageEN;
    }

    public String getTitleZH() {
        return titleZH;
    }

    public void setTitleZH(String titleZH) {
        this.titleZH = titleZH;
    }

    public String getTitleEN() {
        return titleEN;
    }

    public void setTitleEN(String titleEN) {
        this.titleEN = titleEN;
    }

    public PropertiesFromClassPath() {
    }

    public void setMessageZH(String messageZH){
        this.messageZH = messageZH ;
    }
    public String getMessageZH(){
        return this.messageZH ;
    }

    public void setUpgradeFromXAtoXBZH(String upgradeFromXAtoXBZH){
        this.upgradeFromXAtoXBZH = upgradeFromXAtoXBZH;
    }
    public String getUpgradeFromXAtoXBZH(){
        return this.upgradeFromXAtoXBZH ;
    }
    public void setUpgradeFromXAtoXBEN(String upgradeFromXAtoXBEN){
        this.upgradeFromXAtoXBEN = upgradeFromXAtoXBEN;
    }
    public String getUpgradeFromXAtoXBEN(){
        return this.upgradeFromXAtoXBEN ;
    }

    public void setUpgradeFromXAtoXHZH(String upgradeFromXAtoXHZH){
        this.upgradeFromXAtoXHZH = upgradeFromXAtoXHZH;
    }
    public String getUpgradeFromXAtoXHZH(){ return this.upgradeFromXAtoXHZH ; }
    public void setUpgradeFromXAtoXHEN(String upgradeFromXAtoXHEN){
        this.upgradeFromXAtoXHEN = upgradeFromXAtoXHEN;
    }
    public String getUpgradeFromXAtoXHEN(){
        return this.upgradeFromXAtoXHEN ;
    }

    public void setUpgradeFromXBtoXHZH(String upgradeFromXBtoXHZH){
        this.upgradeFromXBtoXHZH = upgradeFromXBtoXHZH;
    }
    public String getUpgradeFromXBtoXHZH(){ return this.upgradeFromXBtoXHZH ; }
    public void setUpgradeFromXBtoXHEN(String upgradeFromXBtoXHEN){
        this.upgradeFromXBtoXHEN = upgradeFromXBtoXHEN;
    }
    public String getUpgradeFromXBtoXHEN(){
        return this.upgradeFromXBtoXHEN ;
    }
}
