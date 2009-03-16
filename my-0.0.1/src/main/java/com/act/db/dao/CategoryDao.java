package com.act.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.act.db.hibernate.HibernateUtil;
import com.act.db.model.Category;


public class CategoryDao extends BaseDao {

        @SuppressWarnings("unchecked")
        public static List<Category> findRoot(){
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("select this.id from Category this where this.level=1 order by this.pos asc");
		query.setCacheable(true);
		query.setCacheRegion("category");
		List<Integer> ids = query.list();
		List<Category> all=new ArrayList<Category>();
		for(int i=0;i<ids.size();i++){
			all.add(loadCategory(ids.get(i)));
		}
		session.close();
		return all;
	}
        
        public static Category loadCategory(Integer id) {
		return (Category) get(Category.class, id);
	}

	
	
}
