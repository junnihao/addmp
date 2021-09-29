package com.example.demo.system.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUtil  implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ApplicationUtil.applicationContext == null) {
            ApplicationUtil.applicationContext = applicationContext;
        }
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }
}
