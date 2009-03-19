package com.act.web.action.manage;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.act.db.model.Category;
import com.act.db.model.User;

/**
 * @author  an_chuantong
 * 
 */
@Controller
@RequestMapping("/user/category/*")
@SessionAttributes("userSession")
public class CategoryManage extends BaseController {

	
	public List<Category> setCategories(){
		return categoryBo.loadRootCategories();
	}

	@RequestMapping
	public String index(ModelMap model,@ModelAttribute("userSession") User user){
		model.addAttribute("categories",categoryBo.loadRootCategories(user.getUsername()));
		return "user/category/index";
	}
	
	@RequestMapping
	public String showEdit(Category category,  ModelMap model,@ModelAttribute("userSession") User user){
		if(category!=null&&category.getId()!=null){
			category=categoryBo.loadCategory(category.getId());
		}
		if(category==null){
			category=new Category();
		}
		model.addAttribute("category", category);
		model.addAttribute("categories",categoryBo.loadRootCategories(user.getUsername()));
		return "user/category/edit";
	}
	
	@RequestMapping
	public String edit(Category category, @RequestParam("parentId") Integer parentId,ModelMap model,@ModelAttribute("userSession") User user){
		Category categoryPo=null;
		boolean succ=categoryBo.saveCategory(categoryPo, categoryPo, parentId, user.getUsername());
		if(succ){
			return "success";
		}
		return "user/category/edit";
	}
	
	@RequestMapping
	public String delete(@RequestParam("id") Integer id,@ModelAttribute("userSession") User user){
		boolean succ=categoryBo.deleteCategory(id, user.getUsername());
		if(succ){
			return "success";
		}
		return "user/category/index";
	}
        
}
