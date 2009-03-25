
package com.act.web.action.manage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.act.bo.CategoryBo;
import com.act.bo.UserBo;
import com.act.db.model.Category;
import com.act.db.model.User;
import com.act.db.model.UserConfig;
import com.act.util.StringUtil;

/**
 * @author an_chuantong
 */

public class UserController extends BaseController {

	protected UserBo userBo;
	
	protected CategoryBo categoryBo;
	@Autowired
	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}


	@Autowired
	public void setCategoryBo(CategoryBo categoryBo) {
		this.categoryBo = categoryBo;
	}

	@ModelAttribute("categories")
	public List<Category> setCategories() {
		return categoryBo.loadRootCategories();
	}

	@SuppressWarnings("unchecked")
	@ModelAttribute("configs")
	public Map<String, Object> setConfigs(@ModelAttribute("userSession") User user, ModelMap model) {
		UserConfig config = userBo.findUserConfig(user.getUsername());
		if (config != null) {
			return StringUtil.str2hash(config.getContent());
		}
		return null;
	}
}
