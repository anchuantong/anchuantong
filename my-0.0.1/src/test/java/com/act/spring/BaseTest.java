package com.act.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;


public class BaseTest extends TestCase {
	public ApplicationContext context;
	protected Log log = LogFactory.getLog(getClass().getName());
	public void setUp(){
		context=new ClassPathXmlApplicationContext("applicationContext-test.xml");
	}
	
	public void test1(){
		
	}
}
