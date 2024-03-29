package com.act.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import com.act.bo.CategoryBo;
import com.act.db.model.Category;


/**
 * @author  an_chuantong
 * 
 */

public class CategoryBoImpl extends BaseBoImpl implements CategoryBo {

	@SuppressWarnings("unchecked")
        public List<Category> loadRootCategories(String username){
		List<Integer> idsList=getDao().findList("select id from Category where level=1 and username=? order by pos asc",new Object[]{username},Boolean.TRUE);
		List<Category> list=new ArrayList<Category>();
		if(idsList!=null&&idsList.size()>0){
			for(int i=0;i<idsList.size();i++){
				list.add(loadCategory(idsList.get(i)));
			}
		}
		return list;
		
	}
	@SuppressWarnings("unchecked")
        public List<Category> loadRootCategories(){
		List<Integer> idsList=getDao().findList("select id from Category where level=1  order by pos asc",Boolean.TRUE);
		List<Category> list=new ArrayList<Category>();
		if(idsList!=null&&idsList.size()>0){
			for(int i=0;i<idsList.size();i++){
				list.add(loadCategory(idsList.get(i)));
			}
		}
		return list;
		
	}
	
	public Category loadCategory(Integer id){
		return (Category) getDao().get(Category.class, id);
	}
	
	public Category loadPicCategory(String username){
	  return (Category) getDao().findOne("from Category  where type=0 and level=1 and username=?", new Object[]{username});
	  
	}
	
	public boolean saveCategory(Category categoryPo,Category category,Integer parentId,String username){
		if(category!=null&&category.getId()!=null){
			categoryPo=loadCategory(category.getId());
			categoryPo.setName(category.getName());
			categoryPo.setLevel(category.getLevel());
			categoryPo.setPassword(category.getPassword());
			categoryPo.setPos(category.getPos());
			categoryPo.setType(category.getType());
		}else{
			categoryPo=category;
		}
		categoryPo.setUsername(username);
		category.setLevel(1);
		if(parentId>0){
			Category parent=loadCategory(parentId);
			if(parent!=null){
				category.setParent(parent);
				category.setLevel(parent.getLevel()+1);
			}
		}
		log.debug("begin save category");
		getDao().save(category);
		return clearCache();
	}
	
	public boolean deleteCategory(Integer id, String username){
		Category category=loadCategory(id);
		log.debug(category.getName());
		List<Category> child= category.getChildren();
		if(child!=null&&child.size()>0){
			for(int i=0;i<child.size();i++){
				getDao().delete(child.get(i));
			}
		}
		getDao().delete(category);
		return clearCache();
	}
	
	public boolean clearCache(){
		try {
			getDao().evict(Category.class);
			getDao().evictCollection(Category.class.getName() + ".children");
			getDao().evictQueries("expopo_category");
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
