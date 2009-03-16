package com.act.db.hibernate;

import java.util.Date;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.act.db.model.Category;
import com.act.db.model.User;


public class HibernateUtilTest extends TestCase {
	public void test1(){
		
	}

//	public void testOne(){
//		Session s =HibernateUtil.getSession();
//		Transaction tx = s.beginTransaction();
//		User user=(User) s.get(User.class, 1);
//		System.out.println(user.getUsername());
//		System.out.println(user.getCreated());
//		s.close();
//	}
//	
//	public void testSave(){
//		Session s =HibernateUtil.getSession();
//		Transaction tx = s.beginTransaction();
//		User user=new User();
//		user.setCreated(new Date());
//		user.setEmail("fffffffff@134.com");
//		user.setPassword("fdsdsf");
//		user.setUsername("tt");
//		s.save(user);
//		tx.rollback();
//		s.close();
//	}
//	
	public void testGetCategory(){
		Session s =HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		Category category=(Category) s.get(Category.class, 1);
		System.out.println(category.getId());
		//System.out.println(category.getUser().getUsername());
	}
}
