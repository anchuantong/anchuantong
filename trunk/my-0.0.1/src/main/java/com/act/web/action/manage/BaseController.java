
package com.act.web.action.manage;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.act.bo.CategoryBo;
import com.act.db.model.Category;
import com.act.util.VelocityUtil;

public class BaseController {

	public Log log = LogFactory.getLog(getClass().getName());

	public CategoryBo categoryBo;

	@Autowired
	public void setCategoryBo(CategoryBo categoryBo) {
		this.categoryBo = categoryBo;
	}

	@ModelAttribute("categories")
	public List<Category> setCategories() {
		return categoryBo.loadRootCategories();
	}

	@ModelAttribute("util")
	public VelocityUtil setUtil() {
		return VelocityUtil.getInstance();
	}

	@ModelAttribute("layout")
	public String setLayout() {
		return "layout/layout.vm";
	}

}
