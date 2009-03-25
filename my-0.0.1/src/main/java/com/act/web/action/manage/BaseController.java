
package com.act.web.action.manage;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.act.db.model.User;
import com.act.db.model.UserConfig;
import com.act.util.StringUtil;
import com.act.util.VelocityUtil;
import com.act.web.util.UserSession;

public class BaseController {

	public Log log = LogFactory.getLog(getClass().getName());


	@ModelAttribute("util")
	public VelocityUtil setUtil() {
		return VelocityUtil.getInstance();
	}

	@ModelAttribute("layout")
	public String setLayout() {
		return "layout/layout.vm";
	}
	
	@ModelAttribute("root")
	public String setRoot(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@ModelAttribute("userSession")
	public UserSession setUserSession(HttpServletRequest request) {
		UserSession user=(UserSession) request.getSession().getAttribute("userSession");
		return user;
	}

}
