
package com.act.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import com.act.bo.UserBo;
import com.act.db.model.User;
import com.act.db.model.UserConfig;
import com.act.util.StringUtil;

/**
 * @author an_chuantong
 */

public class UserBoImpl  extends BaseBoImpl implements UserBo {

	public User findUser(String username) {
		return (User) getDao().findOne("from User where username=?", new Object[]{username});
	}
	
	public UserConfig findUserConfig(String username){
		return (UserConfig) getDao().findOne("from UserConfig where username=?", new Object[]{username});
	}

	public User checkUser(String username, String password){
		return (User) getDao().findOne("from User where username=? and password=?", new Object[]{username,StringUtil.digest(password, "MD5")});
	}
	
	public boolean saveConfig(String username,HttpServletRequest request){
		String fields[]={"bg_atta","bg_image","main_color","menu_color"};
		Map<String, Object>	configs = new HashMap<String, Object>();
		for(int i=0;i<fields.length;i++){
			String value=null;;
                        try {
	                        value = ServletRequestUtils.getStringParameter(request, fields[i]);
                        } catch (ServletRequestBindingException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
                        }
			if(!StringUtil.isEmpty(value)){
				configs.put(fields[i], value);
			}
			UserConfig config=findUserConfig(username);
			if(config==null){
				config=new UserConfig();
				config.setUsername(username);
			}
			config.setContent(StringUtil.hash2str(configs));
			getDao().save(config);
		}
		return true;
	}
	
}
