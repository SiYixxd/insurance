package com.wanghuan.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 由spring 管理的Bean  实现ApplicationContextAware 接口 就可以注入 ApplicationContext容器，
 * 得到容器则可以通过getBean拿到 容器中的Bean
 */
@Component
public class SpringUtil2 implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
