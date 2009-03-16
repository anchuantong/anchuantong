
package com.act.bo.impl;

import java.util.List;

import com.act.bo.UserBo;
import com.act.db.model.User;
import com.act.util.StringUtil;

/**
 * @author an_chuantong
 */

public class UserBoImpl  extends BaseBoImpl implements UserBo {

	public User findUser(String username) {
		return (User) getDao().findOne("from User where username=?", new Object[]{username});
	}

	public User checkUser(String username, String password){
		return (User) getDao().findOne("from User where username=? and password=?", new Object[]{username,StringUtil.digest(password, "MD5")});
	}
}
