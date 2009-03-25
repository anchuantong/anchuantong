
package com.act.web.action.manage;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.act.bean.ConstantBean;
import com.act.bo.CategoryBo;
import com.act.bo.UserBo;
import com.act.db.model.Category;
import com.act.db.model.User;

/**
 * @author an_chuantong
 */
@Controller
@SessionAttributes("userSession")
@RequestMapping("/user/manage/*")
public class UserPortalManage extends UserController {

	private ConstantBean constant;

	@Autowired
	public void setConstant(ConstantBean constant) {
		this.constant = constant;
	}

	@RequestMapping
	public String style(HttpServletRequest request, @ModelAttribute("userSession") User user, ModelMap model) throws ServletRequestBindingException {
		if (request.getMethod().toUpperCase().equals("POST")) {
			userBo.saveConfig(user.getUsername(), request);
			return "success";
		}
		File file = new File(constant.getBasePath(), "css/theme/bg");
		model.addAttribute("bgs", file.listFiles());
		return "user/manage/style";
	}

}
