package com.act.spring;

import com.act.bo.CategoryBo;
import com.act.db.dao.HibernateDao;


public class TestBean extends BaseTest{

	public void test1(){
		Bean2 bean2=(Bean2) context.getBean("bean2");
		bean2.getBean1().sayHello();
	}
	
	public void test2(){
		Bean bean=(Bean) context.getBean("bean");
		bean.getBean().getBean1().sayHello();
	}
	
	public void test3(){
		HibernateDao hibernateDao=(HibernateDao) context.getBean("hibernateDao");
		System.out.println(hibernateDao);
		CategoryBo categoryBo=(CategoryBo) context.getBean("categoryBo");
		System.out.println(categoryBo);
		
	}
}
