package com.act.bo;

import com.act.db.model.User;

/**
 * @author  an_chuantong
 * 
 */

public interface UserBo {

	public User findUser(String username);
	
	public User checkUser(String username,String password);
}