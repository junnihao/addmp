package com.example.demo.springboot.properties;

import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// @Component：标注一个类为Spring容器的Bean，（把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）
// @Value("#{}") 表示SpEl表达式通常用来获取bean的属性，或者调用bean的某个方法。当然还有可以表示常量
// 通过@Value("${}") 可以获取对应属性文件中定义的属性值。假如我有一个sys.properties文件 里面规定了一组值： web.view.prefix
// SpEL(Spring Expression Language),即Spring表达式语言

/*
POJO(Plain Ordinary Java Object)是简单的Java对象，实际就是普通JavaBeans，是为了避免和EJB混淆所创造的简称。
1、它通指没有使用Entity Beans的普通java对象，可以把POJO作为支持业务逻辑的协助类。
2、POJO实质上可以理解为简单的实体类，顾名思义POJO类的作用是方便程序员使用数据库中的数据表，
   对于广大的程序员，可以很方便的将POJO类当做对象来进行使用，当然也是可以方便的调用其get,set方法。POJO类也给我们在struts框架中的配置带来了很大的方便。

   POJO 和JavaBean是我们常见的两个关键字，一般容易混淆。
   POJO全称是Plain Ordinary Java Object / Pure Old Java Object，中文可以翻译成:普通Java类，具有一部分getter/setter方法的那种类就可以称作POJO，
   但是JavaBean则比 POJO复杂很多。
   Java Bean 是可复用的组件，对 Java Bean 并没有严格的规范，理论上讲，任何一个 Java 类都可以是一个 Bean 。但通常情况下，
   由于 Java Bean 是被容器所创建(如 Tomcat) 的，所以 Java Bean 应具有一个无参的构造器，
   另外，通常 Java Bean 还要实现 Serializable 接口用于实现 Bean 的持久性。 Java Bean 是不能被跨进程访问的。JavaBean是一种组件技术。
 */

@Component(value = "NeoProperties") //只有一个参数 value
/*@Scope(scopeName = "prototype")
  bean的作用域
  1、单例对象singleton（缺省）：在spring容器中只存在一个bean的实例，bean以单里的形式存在
  2、多例对象prototype：每次调用getBean()的时候都会返回一个新的实例
  3、request：每次 http 请求都会创建一个 bean， 该作用域仅在基于 web 的 Spring ApplicationContext 情形下有效
  4、session：在一个 HTTP Session 中， 一个 bean 定义对应一个实例（同一个Session共享一个Bean实例）。 该作用域仅在基于 web 的 Spring ApplicationContext 情形下有效。
  5、global-session：global-session：同session作用域不同的是，所有的Session共享一个Bean实例。 该作用域仅在基于 web 的 Spring ApplicationContext 情形下有效。
 */
public class NeoProperties {
    @Value("${com.neo.title}")
    private String title;
    @Value("${com.neo.description}")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
