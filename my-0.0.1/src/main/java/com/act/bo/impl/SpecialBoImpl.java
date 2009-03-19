
package com.act.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.act.bo.SpecialBo;
import com.act.db.model.Article;
import com.act.db.model.ArticlePart;
import com.act.db.model.Special;
import com.act.db.model.SpecialArticle;
import com.act.db.model.SpecialArticlePart;
import com.act.db.model.SpecialModule;
import com.act.enums.ModuleEnum;
import com.act.util.ObjectUtil;
import com.act.util.PageResult;
import com.act.util.StringUtil;

public class SpecialBoImpl extends BaseBoImpl implements SpecialBo {

	// private ArticleBo articleBo;
	//
	// @Autowired
	// public void setArticleBo(ArticleBo articleBo) {
	// this.articleBo = articleBo;
	// }
	//	

	public Special loadSpecial(int id) {
		return (Special) getDao().get(Special.class, id);
	}

	public SpecialArticle loadArticle(Integer id) {
		return (SpecialArticle) getDao().get(SpecialArticle.class, id);
	}

	public Map<String, Object> renderHomepage(int id) {
		Map<String, Object> content = new HashMap<String, Object>();
		Special special = (Special) getDao().get(Special.class, id);

		if (special == null || !special.isPublished()) {
			return null;
		}

		List<SpecialModule> modules = special.getModules();

		if (modules != null && modules.size() > 0) {

			for (int i = 0; i < modules.size(); i++) {
				if (modules.get(i).getType() == ModuleEnum.article.getValue()) {

				}
			}

		}

		content.put("special", special);
		content.put("styles", special.getStyles());
		return content;
	}

	public PageResult search(Map<String, Object> params, int pge, int limit) {
		StringBuffer sql = new StringBuffer(" from Special where 1=1 ");
		List<Object> values = new ArrayList<Object>();
		PageResult pageResult = new PageResult();

		int total = getDao().count("select count(id) " + sql.toString(), values);
		pageResult.setParameters(total, pge, limit);
		pageResult.setResult(getDao().findList(sql.toString(), values, pageResult.getStart(), pageResult.getLimit()));
		return pageResult;
	}

	public Special edit(Special specialForm, String username) {
		Special special = null;
		if (specialForm.getId() != null && specialForm.getId() > 0) {
			special = loadSpecial(specialForm.getId());
		}

		if (special == null) {
			special = new Special();
			special.setCreated(new Date());
			special.setCreator(username);
		}
		ObjectUtil.getInstance().copyObject(specialForm, special);
		special.setModifed(new Date());
		special.setModifer(username);
		getDao().save(special);
		return special;
	}

	public int countArticle(int specialId,boolean approved){
		String sql="select count(id) from SpecialArticle where special.id=?";
		if(approved){
			sql+=" and published=1";
		}
		
		return getDao().count(sql,new Object[]{specialId});
	}

	public List<SpecialArticle> findArticle(int specialId, boolean approved,int start, int limit){
		String sql="from SpecialArticle where special.id=?";
		if(approved){
			sql+=" and published=1";
		}
		sql+=" order by id desc";
		return getDao().findList(sql, new Object[]{specialId}, start,limit);
	}

	public SpecialArticle editArticle(SpecialArticle specialArticleForm, Special special, int part, String username) {
		SpecialArticle article = null;
		if (specialArticleForm.getId() != null && specialArticleForm.getId() > 0) {
			article = loadArticle(specialArticleForm.getId());
		}

		if (article == null) {
			article = new SpecialArticle();
			article.setCreated(new Date());
			article.setCreator(username);
			article.setSpecial(special);

		}
		ObjectUtil.getInstance().copyObject(specialArticleForm, article);
		article.setDescription(StringUtil.summarize(StringUtil.html2txt(specialArticleForm.getDescription()), 150));
		article.setModifed(new Date());
		article.setModifer(username);
		getDao().save(article);
		List<SpecialArticlePart> parts = article.getParts();
		SpecialArticlePart articlePart = null;
		if (parts == null) {
			parts = new ArrayList<SpecialArticlePart>();
			article.setParts(parts);
		}
		if (parts.size() > part) {
			articlePart = article.getParts().get(part);
		} else {
			articlePart = new SpecialArticlePart();
			articlePart.setArticle(article);
			article.getParts().add(articlePart);
		}
		articlePart.setBody(specialArticleForm.getDescription());
		getDao().save(articlePart);
		getDao().update(article);
		return article;
	}

	public void deleteArticle(SpecialArticle article) {
		getDao().delete(article);
	}

	public void addPart(SpecialArticle article) {
		List<SpecialArticlePart> parts = article.getParts();
		SpecialArticlePart articlePart = null;
		if (parts == null) {
			parts = new ArrayList<SpecialArticlePart>();
			article.setParts(parts);
		}
		articlePart = new SpecialArticlePart();
		articlePart.setArticle(article);
		article.getParts().add(articlePart);
		articlePart.setBody("");
		getDao().save(articlePart);
		getDao().update(article);
	}

	public void deletePart(SpecialArticle article, int part) {
		if (article.getParts() != null && article.getParts().size() != 0) {
			SpecialArticlePart articlePart = (SpecialArticlePart) article.getParts().get(part);
			if (article.getParts().size() == 1) {
				articlePart.setBody("");
				getDao().update(articlePart);
			} else {
				article.getParts().remove(part);
				getDao().delete(articlePart);
			}
		}

	}

}
