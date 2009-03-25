package com.act.bo;

import javax.servlet.http.HttpServletRequest;

import com.act.db.model.User;
import com.act.db.model.UserConfig;

/**
 * @author  an_chuantong
 * 
 */

public interface UserBo {

	public User findUser(String username);
	
	public UserConfig findUserConfig(String username);
	
	public User checkUser(String username,String password);
	
	public boolean saveConfig(String username,HttpServletRequest request);
	
}
