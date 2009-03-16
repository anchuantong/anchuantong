
package com.act.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringManager {

	private ApplicationContext context;

	private static SpringManager instance = new SpringManager();

	private SpringManager() {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	public static void init(ApplicationContext p_context) {
		System.out.println("----------------------开始初始化 spring 环境-------------" + p_context);
		instance.context = p_context;
	}

	public static Object getBean(String beanName) {
		return instance.context.getBean(beanName);
	}

}
