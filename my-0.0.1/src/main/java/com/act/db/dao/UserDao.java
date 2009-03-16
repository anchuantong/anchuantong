package com.act.db.dao;

import com.act.db.model.User;


public class UserDao extends BaseDao{

	public static boolean checkLogin(String username,String password){
		User user=(User) findOne("from User where username=? and password=?", new Object[]{username,password});
		return user==null?false:true;
	}
}
