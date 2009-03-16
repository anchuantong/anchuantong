package com.act.bo;

import java.util.Date;

import com.act.db.dao.HibernateDao;
import com.act.db.model.User;
import com.act.spring.BaseTest;
import com.act.util.StringUtil;


/**
 * @author  an_chuantong
 * 
 */

public class UserBoTest extends BaseTest {

	public void test1(){
		User user=new User();
		user.setCreated(new Date());
		user.setEmail("anct125@163.com");
		user.setPassword(StringUtil.digest("111111", "MD5"));
		user.setUsername("anct125");
		user.setId(1);
		((HibernateDao)context.getBean("hibernateDao")).save(user);
	}
}
