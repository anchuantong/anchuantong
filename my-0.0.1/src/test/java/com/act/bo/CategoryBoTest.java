package com.act.bo;

import com.act.spring.BaseTest;

import junit.framework.TestCase;


/**
 * @author  an_chuantong
 * 
 */

public class CategoryBoTest extends BaseTest {

	private CategoryBo categoryBo;
	private UserBo userBo;
	public void setUp(){
		super.setUp();
		categoryBo=(CategoryBo) context.getBean("categoryBo");
		userBo=(UserBo) context.getBean("userBo");
	}
	
	
	public void testLoad(){
		categoryBo.loadRootCategories("anct125");
	}
}
