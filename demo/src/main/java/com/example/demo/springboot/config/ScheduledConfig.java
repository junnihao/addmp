package com.example.demo.springboot.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
@ComponentScan("com.example.demo.springboot.schedule")
public class ScheduledConfig {
    /*也可以将 @Scheduled注释的方法写在这里*/
}
