
package com.act.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.act.bo.UserBo;
import com.act.db.model.User;
import com.act.util.StringUtil;
import com.act.web.util.UserSession;

/**
 * @author an_chuantong
 */
@Controller
@RequestMapping("/login")
@SessionAttributes("userSession")
public class UserLogin {

	private UserBo userBo;

	@RequestMapping(method = RequestMethod.GET)
	public String show() {
		return "user/login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(User user, ModelMap model,HttpServletResponse response) {
		user = userBo.checkUser(user.getUsername(), user.getPassword());
		if (user == null) {
			model.addAttribute("error", "用户名或密码错误！");
			return "user/login";
		} else {
			UserSession userSession = user;
			model.addAttribute("userSession", userSession);
			Cookie cookie = new Cookie("AN_CLIENT_DATA", StringUtil.cipher.encryptString(user.getUsername()));
			cookie.setMaxAge(365*24*3600*1000);
			cookie.setPath("/");
			response.addCookie(cookie);
			return "redirect:../portal/index?id=1";
		}
	}

	@Autowired
	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}
}
