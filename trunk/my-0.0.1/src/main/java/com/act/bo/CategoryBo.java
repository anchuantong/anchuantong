package com.act.bo;

import java.util.List;

import com.act.db.model.Category;

/**
 * @author  an_chuantong
 * 
 */

public interface CategoryBo {

	public List<Category> loadRootCategories(String username);
	
	public List<Category> loadRootCategories();
	
	public Category loadCategory(Integer id);
	
	public Category loadPicCategory(String username);
	
	public boolean saveCategory(Category categoryPo,Category category,Integer parentId,String username);

	public boolean deleteCategory(Integer id, String username);
	
	public boolean clearCache();
	
}
