package com.genie.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring上下文工具
 * @author Genie
 *
 */
@Component("applicationContextAware")
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}
	
	//获取bean
	public static Object getBean(String name) throws BeansException {  
        return applicationContext.getBean(name);
    } 

}
