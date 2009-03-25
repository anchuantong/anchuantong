
package com.act.web.action.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.act.bo.ArticleBo;
import com.act.bo.CategoryBo;
import com.act.bo.UserBo;
import com.act.db.model.Article;
import com.act.db.model.ArticlePart;
import com.act.db.model.Category;
import com.act.db.model.UserConfig;
import com.act.util.PageBuilder;
import com.act.util.StringUtil;
import com.act.web.action.manage.BaseController;

/**
 * @author an_chuantong
 */

@Controller
public class IndexPage extends BaseController {
        protected UserBo userBo;
	
        
	protected CategoryBo categoryBo;
	@Autowired
	public void setUserBo(UserBo userBo) {
		this.userBo = userBo;
	}

	private ArticleBo articleBo;

	@Autowired
	public void setArticleBo(ArticleBo articleBo) {
		this.articleBo = articleBo;
	}
	
	@ModelAttribute("configs")
	public Map<String, Object> setConfigs(HttpServletRequest request, ModelMap model) {
		String ids[] = StringUtil.parse(request.getRequestURI(), 2);
		System.out.println("ggggg:"+ids[0]);
		UserConfig config = userBo.findUserConfig(ids[0]);
		if (config != null) {
			System.out.println(config.getContent());
			return StringUtil.str2hash(config.getContent());
		}
		return null;
	}

	@RequestMapping("/index")
	public String index() {
		return "page/index";
	}

	

}
