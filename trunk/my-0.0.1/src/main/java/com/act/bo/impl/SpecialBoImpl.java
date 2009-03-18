package com.act.bo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.act.bo.ArticleBo;
import com.act.bo.SpecialBo;
import com.act.db.model.Special;
import com.act.db.model.SpecialModule;
import com.act.enums.ModuleEnum;


public class SpecialBoImpl extends BaseBoImpl  implements SpecialBo {

//	private ArticleBo articleBo;
//
//	@Autowired
//	public void setArticleBo(ArticleBo articleBo) {
//		this.articleBo = articleBo;
//	}
//	
	public Map<String, Object>  renderHomepage(int id){
		Map<String, Object> content=new HashMap<String, Object>();
		Special special=(Special) getDao().get(Special.class, id);
		
		if(special==null||!special.isPublished()){
			return null;
		}
		
		List<SpecialModule> modules=special.getModules();
		
		if(modules!=null&&modules.size()>0){
			
			for(int i=0;i<modules.size();i++){
				if(modules.get(i).getType()==ModuleEnum.article.getValue()){
					
				}
			}
			
		}
		
		content.put("special", special);
		content.put("styles", special.getStyles());
		return content;
	}
	
}
