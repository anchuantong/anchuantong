
package com.act.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.act.db.dao.CategoryDao;
import com.act.db.model.Category;
import com.act.db.model.User;
import com.act.web.action.manage.BaseController;

@Controller
@SessionAttributes("userSession")
public class TestController extends BaseController {
	
	
	public List<Category> setCategories(){
		return CategoryDao.findRoot();
	}
	
	@RequestMapping("/test/index")
	public String index(@ModelAttribute("userSession") User user,ModelMap model ) {
		System.out.println("index:"+user);
		
		return "portal";
	}
}
