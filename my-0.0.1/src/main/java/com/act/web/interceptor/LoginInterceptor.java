
package com.act.web.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.act.bo.UserBo;
import com.act.db.model.User;
import com.act.util.StringUtil;
import com.act.web.util.Config;
import com.act.web.util.UserSession;

/**
 * @author an_chuantong
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private UserBo userBo;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, Config.SESSION_NAME);
		if (userSession == null) {
			Cookie cookie = WebUtils.getCookie(request, "AN_CLIENT_DATA");
			if (cookie != null) {
				String username = cookie.getValue();
				if (!StringUtil.isEmpty(username)) {
					username = StringUtil.cipher.decryptString(username);
					User user = userBo.findUser(username);
					if (user != null) {
						userSession = user;
						WebUtils.setSessionAttribute(request, Config.SESSION_NAME, userSession);
					}
				}

			}
			if (userSession == null) {
				if (request.getRequestURI().indexOf("/special/") > -1) {
					User user = new User();
					user.setUsername("anonymous");
					userSession = user;
					WebUtils.setSessionAttribute(request, Config.SESSION_NAME, userSession);
				} else {
					response.sendRedirect(request.getContextPath() + "/portal/login");
					return false;
				}
			}
		}
		return true;
	}

	@Autowired
	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

}
