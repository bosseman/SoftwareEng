package com.CardTracker.SoftwareEng;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/*
 * Returns bean context
 */
public class SpringApplicationContext implements ApplicationContextAware{
	private static ApplicationContext CONTEXT;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;
		
	}

	public static Object getBean(String beanName) {
		return CONTEXT.getBean(beanName);
	}
}
