package com.act.bo;

import java.util.List;
import java.util.Map;

import com.act.db.model.Article;
import com.act.db.model.Category;
import com.act.util.PageResult;

/**
 * @author  an_chuantong
 * 
 */

public interface ArticleBo {

	/**
	 * 查询文章列表
	 * @param params
	 * @param pge
	 * @param limit
	 * @return
	 */
	public PageResult search(Map<String, Object> params,int pge,int limit);
	
	/**
	 * 保存修改文章
	 * @param article
	 * @param body
	 * @param part
	 * @return
	 */
	public boolean saveArticle(Article article,String body,String partTitle,int part);
	
	public boolean saveArticleSmall(Article article);
	
	public void deleteArticle(Article article);

	public Article loadArticle(Integer id);
	
	/**
	 * 统计某分类下文章列表
	 * approved等于true只统计已发布
	 * @param cat
	 * @param approved
	 * @return
	 */
	public Integer countArticleByCat(Category cat,boolean approved);
	
	public List<Article> findArticleByCat(Category cat,boolean approved);

	public void addPart(Article article);
	
	public void deletePart(Article article,int part);

	
}
